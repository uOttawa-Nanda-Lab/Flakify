/*
 * Copyright (C) 2014 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.ResponseSource;
import com.squareup.okhttp.internal.SslContextBuilder;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.ResponseCache;
import java.net.SecureCacheResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Principal;
import java.security.cert.Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPOutputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

import static com.squareup.okhttp.mockwebserver.SocketPolicy.DISCONNECT_AT_END;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for interaction between OkHttp and the ResponseCache. This test is based on
 * {@link com.squareup.okhttp.internal.http.HttpResponseCacheTest}. Some tests for the
 * {@link com.squareup.okhttp.OkResponseCache} found in HttpResponseCacheTest provide
 * coverage for ResponseCache as well.
 */
public final class ResponseCacheTest {
  private static final HostnameVerifier NULL_HOSTNAME_VERIFIER = new HostnameVerifier() {
    @Override public boolean verify(String s, SSLSession sslSession) {
      return true;
    }
  };

  private static final SSLContext sslContext = SslContextBuilder.localhost();

  private OkHttpClient client;
  private MockWebServer server;
  private MockWebServer server2;

  @Before public void setUp() throws Exception {
    server =  new MockWebServer();
    server.setNpnEnabled(false);
    server2 =  new MockWebServer();

    client = new OkHttpClient();
    ResponseCache cache = new InMemoryResponseCache();
    client.setResponseCache(cache);
  }

  private HttpURLConnection openConnection(URL url) {
    return client.open(url);
  }

  /**
   * HttpURLConnection.getInputStream().skip(long) causes ResponseCache corruption
   * http://code.google.com/p/android/issues/detail?id=8175
   */
  private void testResponseCaching(TransferKind transferKind) throws IOException {
    MockResponse response =
        new MockResponse().addHeader("Last-Modified: " + formatDate(-1, TimeUnit.HOURS))
            .addHeader("Expires: " + formatDate(1, TimeUnit.HOURS))
            .setStatus("HTTP/1.1 200 Fantastic");
    transferKind.setBody(response, "I love puppies but hate spiders", 1);
    server.enqueue(response);
    server.play();

    // Make sure that calling skip() doesn't omit bytes from the cache.
    HttpURLConnection urlConnection = openConnection(server.getUrl("/"));
    InputStream in = urlConnection.getInputStream();
    assertEquals("I love ", readAscii(urlConnection, "I love ".length()));
    reliableSkip(in, "puppies but hate ".length());
    assertEquals("spiders", readAscii(urlConnection, "spiders".length()));
    assertEquals(-1, in.read());
    in.close();

    urlConnection = openConnection(server.getUrl("/")); // cached!
    in = urlConnection.getInputStream();
    assertEquals("I love puppies but hate spiders",
        readAscii(urlConnection, "I love puppies but hate spiders".length()));
    assertEquals(200, urlConnection.getResponseCode());
    assertEquals("Fantastic", urlConnection.getResponseMessage());

    assertEquals(-1, in.read());
    in.close();
  }

  private void testServerPrematureDisconnect(TransferKind transferKind) throws IOException {
    MockResponse response = new MockResponse();
    transferKind.setBody(response, "ABCDE\nFGHIJKLMNOPQRSTUVWXYZ", 16);
    server.enqueue(truncateViolently(response, 16));
    server.enqueue(new MockResponse().setBody("Request #2"));
    server.play();

    BufferedReader reader = new BufferedReader(
        new InputStreamReader(openConnection(server.getUrl("/")).getInputStream()));
    assertEquals("ABCDE", reader.readLine());
    try {
      reader.readLine();
      fail("This implementation silently ignored a truncated HTTP body.");
    } catch (IOException expected) {
        expected.printStackTrace();
    } finally {
      reader.close();
    }

    URLConnection connection = openConnection(server.getUrl("/"));
    assertEquals("Request #2", readAscii(connection));
  }

  private void testClientPrematureDisconnect(TransferKind transferKind) throws IOException {
    // Setting a low transfer speed ensures that stream discarding will time out.
    MockResponse response = new MockResponse().throttleBody(6, 1, TimeUnit.SECONDS);
    transferKind.setBody(response, "ABCDE\nFGHIJKLMNOPQRSTUVWXYZ", 1024);
    server.enqueue(response);
    server.enqueue(new MockResponse().setBody("Request #2"));
    server.play();

    URLConnection connection = openConnection(server.getUrl("/"));
    InputStream in = connection.getInputStream();
    assertEquals("ABCDE", readAscii(connection, 5));
    in.close();
    try {
      in.read();
      fail("Expected an IOException because the stream is closed.");
    } catch (IOException expected) {
    }

    connection = openConnection(server.getUrl("/"));
    assertEquals("Request #2", readAscii(connection));
  }

  private void testRequestMethod(String requestMethod, boolean expectCached) throws Exception {
    // 1. seed the cache (potentially)
    // 2. expect a cache hit or miss
    server.enqueue(new MockResponse().addHeader("Expires: " + formatDate(1, TimeUnit.HOURS))
        .addHeader("X-Response-ID: 1"));
    server.enqueue(new MockResponse().addHeader("X-Response-ID: 2"));
    server.play();

    URL url = server.getUrl("/");

    HttpURLConnection request1 = openConnection(url);
    request1.setRequestMethod(requestMethod);
    addRequestBodyIfNecessary(requestMethod, request1);
    assertEquals("1", request1.getHeaderField("X-Response-ID"));

    URLConnection request2 = openConnection(url);
    if (expectCached) {
      assertEquals("1", request2.getHeaderField("X-Response-ID"));
    } else {
      assertEquals("2", request2.getHeaderField("X-Response-ID"));
    }
  }

  private void assertNonIdentityEncodingCached(MockResponse response) throws Exception {
    server.enqueue(
        response.setBody(gzip("ABCABCABC".getBytes("UTF-8"))).addHeader("Content-Encoding: gzip"));
    server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED));
    server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED));

    server.play();

    // At least three request/response pairs are required because after the first request is cached
    // a different execution path might be taken. Thus modifications to the cache applied during
    // the second request might not be visible until another request is performed.
    assertEquals("ABCABCABC", readAscii(openConnection(server.getUrl("/"))));
    assertEquals("ABCABCABC", readAscii(openConnection(server.getUrl("/"))));
    assertEquals("ABCABCABC", readAscii(openConnection(server.getUrl("/"))));
  }

  private RecordedRequest assertClientSuppliedCondition(MockResponse seed, String conditionName,
      String conditionValue) throws Exception {
    server.enqueue(seed.setBody("A"));
    server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED));
    server.play();

    URL url = server.getUrl("/");
    assertEquals("A", readAscii(openConnection(url)));

    HttpURLConnection connection = openConnection(url);
    connection.addRequestProperty(conditionName, conditionValue);
    assertEquals(HttpURLConnection.HTTP_NOT_MODIFIED, connection.getResponseCode());
    assertEquals("", readAscii(connection));

    server.takeRequest(); // seed
    return server.takeRequest();
  }

  @Test public void responseSourceHeaderConditionalCacheFetched() throws IOException {
    server.enqueue(new MockResponse().setBody("A")
        .addHeader("Cache-Control: max-age=30")
        .addHeader("Date: " + formatDate(-31, TimeUnit.MINUTES)));
    server.enqueue(new MockResponse().setBody("B")
        .addHeader("Cache-Control: max-age=30")
        .addHeader("Date: " + formatDate(0, TimeUnit.MINUTES)));
    server.play();

    assertEquals("A", readAscii(openConnection(server.getUrl("/"))));
    HttpURLConnection connection = openConnection(server.getUrl("/"));
    assertEquals("B", readAscii(connection));

    String source = connection.getHeaderField(OkHeaders.RESPONSE_SOURCE);
    assertEquals(ResponseSource.CONDITIONAL_CACHE + " 200", source);
  }

  /**
   * @param delta the offset from the current date to use. Negative
   * values yield dates in the past; positive values yield dates in the
   * future.
   */
  private String formatDate(long delta, TimeUnit timeUnit) {
    return formatDate(new Date(System.currentTimeMillis() + timeUnit.toMillis(delta)));
  }

  private String formatDate(Date date) {
    DateFormat rfc1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
    rfc1123.setTimeZone(TimeZone.getTimeZone("GMT"));
    return rfc1123.format(date);
  }

  private void addRequestBodyIfNecessary(String requestMethod, HttpURLConnection invalidate)
      throws IOException {
    if (requestMethod.equals("POST") || requestMethod.equals("PUT")) {
      invalidate.setDoOutput(true);
      OutputStream requestBody = invalidate.getOutputStream();
      requestBody.write('x');
      requestBody.close();
    }
  }

  private void assertNotCached(MockResponse response) throws Exception {
    server.enqueue(response.setBody("A"));
    server.enqueue(new MockResponse().setBody("B"));
    server.play();

    URL url = server.getUrl("/");
    assertEquals("A", readAscii(openConnection(url)));
    assertEquals("B", readAscii(openConnection(url)));
  }

  /** @return the request with the conditional get headers. */
  private RecordedRequest assertConditionallyCached(MockResponse response) throws Exception {
    // scenario 1: condition succeeds
    server.enqueue(response.setBody("A").setStatus("HTTP/1.1 200 A-OK"));
    server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED));

    // scenario 2: condition fails
    server.enqueue(response.setBody("B").setStatus("HTTP/1.1 200 B-OK"));
    server.enqueue(new MockResponse().setStatus("HTTP/1.1 200 C-OK").setBody("C"));

    server.play();

    URL valid = server.getUrl("/valid");
    HttpURLConnection connection1 = openConnection(valid);
    assertEquals("A", readAscii(connection1));
    assertEquals(HttpURLConnection.HTTP_OK, connection1.getResponseCode());
    assertEquals("A-OK", connection1.getResponseMessage());
    HttpURLConnection connection2 = openConnection(valid);
    assertEquals("A", readAscii(connection2));
    assertEquals(HttpURLConnection.HTTP_OK, connection2.getResponseCode());
    assertEquals("A-OK", connection2.getResponseMessage());

    URL invalid = server.getUrl("/invalid");
    HttpURLConnection connection3 = openConnection(invalid);
    assertEquals("B", readAscii(connection3));
    assertEquals(HttpURLConnection.HTTP_OK, connection3.getResponseCode());
    assertEquals("B-OK", connection3.getResponseMessage());
    HttpURLConnection connection4 = openConnection(invalid);
    assertEquals("C", readAscii(connection4));
    assertEquals(HttpURLConnection.HTTP_OK, connection4.getResponseCode());
    assertEquals("C-OK", connection4.getResponseMessage());

    server.takeRequest(); // regular get
    return server.takeRequest(); // conditional get
  }

  private void assertFullyCached(MockResponse response) throws Exception {
    server.enqueue(response.setBody("A"));
    server.enqueue(response.setBody("B"));
    server.play();

    URL url = server.getUrl("/");
    assertEquals("A", readAscii(openConnection(url)));
    assertEquals("A", readAscii(openConnection(url)));
  }

  /**
   * Shortens the body of {@code response} but not the corresponding headers.
   * Only useful to test how clients respond to the premature conclusion of
   * the HTTP body.
   */
  private MockResponse truncateViolently(MockResponse response, int numBytesToKeep) {
    response.setSocketPolicy(DISCONNECT_AT_END);
    List<String> headers = new ArrayList<String>(response.getHeaders());
    response.setBody(Arrays.copyOfRange(response.getBody(), 0, numBytesToKeep));
    response.getHeaders().clear();
    response.getHeaders().addAll(headers);
    return response;
  }

  /**
   * Reads {@code count} characters from the stream. If the stream is
   * exhausted before {@code count} characters can be read, the remaining
   * characters are returned and the stream is closed.
   */
  private String readAscii(URLConnection connection, int count) throws IOException {
    HttpURLConnection httpConnection = (HttpURLConnection) connection;
    InputStream in = httpConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST
        ? connection.getInputStream() : httpConnection.getErrorStream();
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < count; i++) {
      int value = in.read();
      if (value == -1) {
        in.close();
        break;
      }
      result.append((char) value);
    }
    return result.toString();
  }

  private String readAscii(URLConnection connection) throws IOException {
    return readAscii(connection, Integer.MAX_VALUE);
  }

  private void reliableSkip(InputStream in, int length) throws IOException {
    while (length > 0) {
      length -= in.skip(length);
    }
  }

  private void assertGatewayTimeout(HttpURLConnection connection) throws IOException {
    try {
      connection.getInputStream();
      fail();
    } catch (FileNotFoundException expected) {
    }
    assertEquals(504, connection.getResponseCode());
    assertEquals(-1, connection.getErrorStream().read());
    assertEquals(ResponseSource.NONE + " 504",
        connection.getHeaderField(OkHeaders.RESPONSE_SOURCE));
  }

  enum TransferKind {
    CHUNKED() {
      
    },
    FIXED_LENGTH() {
      
    },
    END_OF_STREAM() {
      
    };

    abstract void setBody(MockResponse response, byte[] content, int chunkSize) throws IOException;

    void setBody(MockResponse response, String content, int chunkSize) throws IOException {
      setBody(response, content.getBytes("UTF-8"), chunkSize);
    }
  }

  private <T> List<T> toListOrNull(T[] arrayOrNull) {
    return arrayOrNull != null ? Arrays.asList(arrayOrNull) : null;
  }

  private static class InsecureResponseCache extends ResponseCache {

    private final ResponseCache delegate;

    private InsecureResponseCache(ResponseCache delegate) {
      this.delegate = delegate;
    }

    @Override public CacheRequest put(URI uri, URLConnection connection) throws IOException {
      return delegate.put(uri, connection);
    }

    @Override public CacheResponse get(URI uri, String requestMethod,
        Map<String, List<String>> requestHeaders) throws IOException {
      final CacheResponse response = delegate.get(uri, requestMethod, requestHeaders);
      if (response instanceof SecureCacheResponse) {
        return new CacheResponse() {
          @Override public InputStream getBody() throws IOException {
            return response.getBody();
          }
          @Override public Map<String, List<String>> getHeaders() throws IOException {
            return response.getHeaders();
          }
        };
      }
      return response;
    }
  }

  /**
   * A trivial and non-thread-safe implementation of ResponseCache that uses an in-memory map to
   * cache GETs.
   */
  private static class InMemoryResponseCache extends ResponseCache {

    /** A request / response header that acts a bit like Vary but without the complexity. */
    public static final String CACHE_VARIANT_HEADER = "CacheVariant";

    private static class Key {
      private final URI uri;
      private final String cacheVariant;

      private Key(URI uri, String cacheVariant) {
        this.uri = uri;
        this.cacheVariant = cacheVariant;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (o == null || getClass() != o.getClass()) {
          return false;
        }

        Key key = (Key) o;

        if (cacheVariant != null ? !cacheVariant.equals(key.cacheVariant)
            : key.cacheVariant != null) {
          return false;
        }
        if (!uri.equals(key.uri)) {
          return false;
        }

        return true;
      }

      @Override
      public int hashCode() {
        int result = uri.hashCode();
        result = 31 * result + (cacheVariant != null ? cacheVariant.hashCode() : 0);
        return result;
      }
    }

    private class Entry {

      private final URI uri;
      private final String cacheVariant;
      private final String method;
      private final Map<String, List<String>> responseHeaders;
      private final String cipherSuite;
      private final Certificate[] serverCertificates;
      private final Certificate[] localCertificates;
      private byte[] body;

      public Entry(URI uri, URLConnection urlConnection) {
        this.uri = uri;
        HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
        method = httpUrlConnection.getRequestMethod();
        cacheVariant = urlConnection.getHeaderField(CACHE_VARIANT_HEADER);
        responseHeaders = urlConnection.getHeaderFields();
        if (urlConnection instanceof HttpsURLConnection) {
          HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urlConnection;
          cipherSuite = httpsURLConnection.getCipherSuite();
          Certificate[] serverCertificates;
          try {
            serverCertificates = httpsURLConnection.getServerCertificates();
          } catch (SSLPeerUnverifiedException e) {
            serverCertificates = null;
          }
          this.serverCertificates = serverCertificates;
          localCertificates = httpsURLConnection.getLocalCertificates();
        } else {
          cipherSuite = null;
          serverCertificates = null;
          localCertificates = null;
        }
      }

      public CacheResponse asCacheResponse() {
        if (!method.equals(this.method)) {
          return null;
        }

        // Handle SSL
        if (cipherSuite != null) {
          return new SecureCacheResponse() {
            @Override
            public Map<String, List<String>> getHeaders() throws IOException {
              return responseHeaders;
            }

            @Override
            public InputStream getBody() throws IOException {
              return new ByteArrayInputStream(body);
            }

            @Override
            public String getCipherSuite() {
              return cipherSuite;
            }

            @Override
            public List<Certificate> getLocalCertificateChain() {
              return localCertificates == null ? null : Arrays.asList(localCertificates);
            }

            @Override
            public List<Certificate> getServerCertificateChain() throws SSLPeerUnverifiedException {
              if (serverCertificates == null) {
                throw new SSLPeerUnverifiedException("Test implementation");
              }
              return Arrays.asList(serverCertificates);
            }

            @Override
            public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
              throw new UnsupportedOperationException();
            }

            @Override
            public Principal getLocalPrincipal() {
              throw new UnsupportedOperationException();
            }
          };
        } else {
          return new CacheResponse() {
            @Override
            public Map<String, List<String>> getHeaders() throws IOException {
              return responseHeaders;
            }

            @Override
            public InputStream getBody() throws IOException {
              return new ByteArrayInputStream(body);
            }
          };
        }
      }

      private Key key() {
        return new Key(uri, cacheVariant);
      }
    }

    private Map<Key, Entry> cache = new HashMap<Key, Entry>();
  }
}
