/*
 * Copyright (C) 2011 The Android Open Source Project
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

import com.squareup.okhttp.HttpResponseCache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkResponseCache;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseSource;
import com.squareup.okhttp.internal.SslContextBuilder;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.CookieHandler;
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
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.squareup.okhttp.mockwebserver.SocketPolicy.DISCONNECT_AT_END;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Android's HttpResponseCacheTest. This tests both the {@link HttpResponseCache} implementation and
 * the behavior of {@link com.squareup.okhttp.OkResponseCache} classes generally.
 */
public final class HttpResponseCacheTest {
  private static final HostnameVerifier NULL_HOSTNAME_VERIFIER = new HostnameVerifier() {
    @Override public boolean verify(String s, SSLSession sslSession) {
      return true;
    }
  };

  private static final SSLContext sslContext = SslContextBuilder.localhost();

  private final OkHttpClient client = new OkHttpClient();
  private MockWebServer server = new MockWebServer();
  private MockWebServer server2 = new MockWebServer();
  private HttpResponseCache cache;
  private final CookieManager cookieManager = new CookieManager();

  @Before public void setUp() throws Exception {
    String tmp = System.getProperty("java.io.tmpdir");
    File cacheDir = new File(tmp, "HttpCache-" + UUID.randomUUID());
    cache = new HttpResponseCache(cacheDir, Integer.MAX_VALUE);
    ResponseCache.setDefault(cache);
    CookieHandler.setDefault(cookieManager);
    server.setNpnEnabled(false);
  }

  private HttpURLConnection openConnection(URL url) {
    return client.open(url);
  }

  private void assertCached(boolean shouldPut, int responseCode) throws Exception {
    server = new MockWebServer();
    MockResponse response =
        new MockResponse().addHeader("Last-Modified: " + formatDate(-1, TimeUnit.HOURS))
            .addHeader("Expires: " + formatDate(1, TimeUnit.HOURS))
            .setResponseCode(responseCode)
            .setBody("ABCDE")
            .addHeader("WWW-Authenticate: challenge");
    if (responseCode == HttpURLConnection.HTTP_PROXY_AUTH) {
      response.addHeader("Proxy-Authenticate: Basic realm=\"protected area\"");
    } else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
      response.addHeader("WWW-Authenticate: Basic realm=\"protected area\"");
    }
    server.enqueue(response);
    server.play();

    URL url = server.getUrl("/");
    HttpURLConnection conn = openConnection(url);
    assertEquals(responseCode, conn.getResponseCode());

    // exhaust the content stream
    readAscii(conn);

    Response cached = cache.get(new Request.Builder().url(url).build());
    if (shouldPut) {
      assertNotNull(Integer.toString(responseCode), cached);
      cached.body().close();
    } else {
      assertNull(Integer.toString(responseCode), cached);
    }
    server.shutdown(); // tearDown() isn't sufficient; this test starts multiple servers
  }

  /**
 * We've had bugs where caching and cross-protocol redirects yield class cast exceptions internal to the cache because we incorrectly assumed that HttpsURLConnection was always HTTPS and HttpURLConnection was always HTTP; in practice redirects mean that each can do either. https://github.com/square/okhttp/issues/214
 */@Test public void secureResponseCachingAndProtocolRedirects() throws IOException{server2.useHttps(sslContext.getSocketFactory(),false);server2.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(-1,TimeUnit.HOURS)).addHeader("Expires: " + formatDate(1,TimeUnit.HOURS)).setBody("ABC"));server2.enqueue(new MockResponse().setBody("DEF"));server2.play();server.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(-1,TimeUnit.HOURS)).addHeader("Expires: " + formatDate(1,TimeUnit.HOURS)).setResponseCode(HttpURLConnection.HTTP_MOVED_PERM).addHeader("Location: " + server2.getUrl("/")));server.play();client.setSslSocketFactory(sslContext.getSocketFactory());client.setHostnameVerifier(NULL_HOSTNAME_VERIFIER);HttpURLConnection connection1=client.open(server.getUrl("/"));assertEquals("ABC",readAscii(connection1));HttpURLConnection connection2=client.open(server.getUrl("/"));assertEquals("ABC",readAscii(connection2));assertEquals(4,cache.getRequestCount());assertEquals(2,cache.getHitCount());}

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

  private void writeFile(File directory, String file, String content) throws IOException {
    OutputStream out = new FileOutputStream(new File(directory, file));
    out.write(content.getBytes(Util.UTF_8));
    out.close();
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
        ? connection.getInputStream()
        : httpConnection.getErrorStream();
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

  private class InsecureResponseCache extends ResponseCache {
    @Override public CacheRequest put(URI uri, URLConnection connection) throws IOException {
      return cache.put(uri, connection);
    }

    @Override public CacheResponse get(URI uri, String requestMethod,
        Map<String, List<String>> requestHeaders) throws IOException {
      final CacheResponse response = cache.get(uri, requestMethod, requestHeaders);
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

  static abstract class AbstractOkResponseCache implements OkResponseCache {
    @Override public Response get(Request request) throws IOException {
      return null;
    }

    @Override public CacheRequest put(Response response) throws IOException {
      return null;
    }

    @Override public boolean maybeRemove(Request request) throws IOException {
      return false;
    }

    @Override public void update(Response cached, Response network) throws IOException {
    }

    @Override public void trackConditionalCacheHit() {
    }

    @Override public void trackResponse(ResponseSource source) {
    }
  }
}
