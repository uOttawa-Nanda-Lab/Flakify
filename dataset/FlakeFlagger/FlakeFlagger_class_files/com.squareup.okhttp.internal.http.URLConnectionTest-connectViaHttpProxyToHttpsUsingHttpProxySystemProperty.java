/*
 * Copyright (C) 2009 The Android Open Source Project
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
import com.squareup.okhttp.OkAuthenticator.Credential;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.RecordingAuthenticator;
import com.squareup.okhttp.internal.RecordingHostnameVerifier;
import com.squareup.okhttp.internal.RecordingOkAuthenticator;
import com.squareup.okhttp.internal.SslContextBuilder;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import com.squareup.okhttp.mockwebserver.SocketPolicy;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.ResponseCache;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static com.squareup.okhttp.internal.Util.UTF_8;
import static com.squareup.okhttp.internal.http.OkHeaders.SELECTED_PROTOCOL;
import static com.squareup.okhttp.internal.http.StatusLine.HTTP_TEMP_REDIRECT;
import static com.squareup.okhttp.mockwebserver.SocketPolicy.DISCONNECT_AT_END;
import static com.squareup.okhttp.mockwebserver.SocketPolicy.DISCONNECT_AT_START;
import static com.squareup.okhttp.mockwebserver.SocketPolicy.SHUTDOWN_INPUT_AT_END;
import static com.squareup.okhttp.mockwebserver.SocketPolicy.SHUTDOWN_OUTPUT_AT_END;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/** Android's URLConnectionTest. */
public final class URLConnectionTest {
  private static final SSLContext sslContext = SslContextBuilder.localhost();

  private MockWebServer server = new MockWebServer();
  private MockWebServer server2 = new MockWebServer();

  private final OkHttpClient client = new OkHttpClient();
  private HttpURLConnection connection;
  private HttpResponseCache cache;
  private String hostName;

  @Before public void setUp() throws Exception {
    hostName = server.getHostName();
    server.setNpnEnabled(false);
  }

  enum WriteKind {BYTE_BY_BYTE, SMALL_BUFFERS, LARGE_BUFFERS}

  private void doUpload(TransferKind uploadKind, WriteKind writeKind) throws Exception {
    int n = 512 * 1024;
    server.setBodyLimit(0);
    server.enqueue(new MockResponse());
    server.play();

    HttpURLConnection conn = client.open(server.getUrl("/"));
    conn.setDoOutput(true);
    conn.setRequestMethod("POST");
    if (uploadKind == TransferKind.CHUNKED) {
      conn.setChunkedStreamingMode(-1);
    } else {
      conn.setFixedLengthStreamingMode(n);
    }
    OutputStream out = conn.getOutputStream();
    if (writeKind == WriteKind.BYTE_BY_BYTE) {
      for (int i = 0; i < n; ++i) {
        out.write('x');
      }
    } else {
      byte[] buf = new byte[writeKind == WriteKind.SMALL_BUFFERS ? 256 : 64 * 1024];
      Arrays.fill(buf, (byte) 'x');
      for (int i = 0; i < n; i += buf.length) {
        out.write(buf, 0, Math.min(buf.length, n - i));
      }
    }
    out.close();
    assertEquals(200, conn.getResponseCode());
    RecordedRequest request = server.takeRequest();
    assertEquals(n, request.getBodySize());
    if (uploadKind == TransferKind.CHUNKED) {
      assertTrue(request.getChunkSizes().size() > 0);
    } else {
      assertTrue(request.getChunkSizes().isEmpty());
    }
  }

  @Test public void connectViaHttpProxyToHttpsUsingHttpProxySystemProperty() throws Exception{testConnectViaDirectProxyToHttps(ProxyConfig.HTTP_PROXY_SYSTEM_PROPERTY);}

  private void initResponseCache() throws IOException {
    String tmp = System.getProperty("java.io.tmpdir");
    File cacheDir = new File(tmp, "HttpCache-" + UUID.randomUUID());
    cache = new HttpResponseCache(cacheDir, Integer.MAX_VALUE);
    client.setOkResponseCache(cache);
  }

  /**
   * Reads {@code count} characters from the stream. If the stream is
   * exhausted before {@code count} characters can be read, the remaining
   * characters are returned and the stream is closed.
   */
  private String readAscii(InputStream in, int count) throws IOException {
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

  private List<String> authCallsForHeader(String authHeader) throws IOException {
    boolean proxy = authHeader.startsWith("Proxy-");
    int responseCode = proxy ? 407 : 401;
    RecordingAuthenticator authenticator = new RecordingAuthenticator(null);
    Authenticator.setDefault(authenticator);
    MockResponse pleaseAuthenticate = new MockResponse().setResponseCode(responseCode)
        .addHeader(authHeader)
        .setBody("Please authenticate.");
    server.enqueue(pleaseAuthenticate);
    server.play();

    if (proxy) {
      client.setProxy(server.toProxyAddress());
      connection = client.open(new URL("http://android.com"));
    } else {
      connection = client.open(server.getUrl("/"));
    }
    assertEquals(responseCode, connection.getResponseCode());
    return authenticator.calls;
  }

  private void assertValidRequestMethod(String requestMethod) throws Exception {
    connection = client.open(server.getUrl("/"));
    connection.setRequestMethod(requestMethod);
    assertEquals(requestMethod, connection.getRequestMethod());
  }

  private void assertInvalidRequestMethod(String requestMethod) throws Exception {
    connection = client.open(server.getUrl("/"));
    try {
      connection.setRequestMethod(requestMethod);
      fail();
    } catch (ProtocolException expected) {
    }
  }

  enum StreamingMode {
    FIXED_LENGTH, CHUNKED
  }

  private void reusedConnectionFailsWithPost(TransferKind transferKind, int requestSize)
      throws Exception {
    server.enqueue(new MockResponse().setBody("A").setSocketPolicy(SHUTDOWN_INPUT_AT_END));
    server.enqueue(new MockResponse().setBody("B"));
    server.enqueue(new MockResponse().setBody("C"));
    server.play();

    assertContent("A", client.open(server.getUrl("/a")));

    // If the request body is larger than OkHttp's replay buffer, the failure may still occur.
    byte[] requestBody = new byte[requestSize];
    new Random(0).nextBytes(requestBody);

    connection = client.open(server.getUrl("/b"));
    connection.setRequestMethod("POST");
    transferKind.setForRequest(connection, requestBody.length);
    for (int i = 0; i < requestBody.length; i += 1024) {
      connection.getOutputStream().write(requestBody, i, 1024);
    }
    connection.getOutputStream().close();
    assertContent("B", connection);

    RecordedRequest requestA = server.takeRequest();
    assertEquals("/a", requestA.getPath());
    RecordedRequest requestB = server.takeRequest();
    assertEquals("/b", requestB.getPath());
    assertEquals(Arrays.toString(requestBody), Arrays.toString(requestB.getBody()));
  }

  private void npnSetsProtocolHeader(Protocol protocol) throws IOException {
    enableNpn(protocol);
    server.enqueue(new MockResponse().setBody("A"));
    server.play();
    client.setProtocols(Arrays.asList(Protocol.HTTP_11, protocol));
    connection = client.open(server.getUrl("/"));
    List<String> protocolValues = connection.getHeaderFields().get(SELECTED_PROTOCOL);
    assertEquals(Arrays.asList(protocol.name.utf8()), protocolValues);
    assertContent("A", connection);
  }

  private void zeroLengthPayload(String method)
      throws IOException, InterruptedException {
    server.enqueue(new MockResponse());
    server.play();
    connection = client.open(server.getUrl("/"));
    connection.setRequestProperty("Content-Length", "0");
    connection.setRequestMethod(method);
    connection.setFixedLengthStreamingMode(0);
    connection.setDoOutput(true);
    assertContent("", connection);
    RecordedRequest zeroLengthPayload = server.takeRequest();
    assertEquals(method, zeroLengthPayload.getMethod());
    assertEquals("0", zeroLengthPayload.getHeader("content-length"));
    assertEquals(0L, zeroLengthPayload.getBodySize());
  }

  /**
   * Reads at most {@code limit} characters from {@code in} and asserts that
   * content equals {@code expected}.
   */
  private void assertContent(String expected, HttpURLConnection connection, int limit)
      throws IOException {
    connection.connect();
    assertEquals(expected, readAscii(connection.getInputStream(), limit));
  }

  private void assertContent(String expected, HttpURLConnection connection) throws IOException {
    assertContent(expected, connection, Integer.MAX_VALUE);
  }

  private void assertContains(List<String> headers, String header) {
    assertTrue(headers.toString(), headers.contains(header));
  }

  private void assertContainsNoneMatching(List<String> headers, String pattern) {
    for (String header : headers) {
      if (header.matches(pattern)) {
        fail("Header " + header + " matches " + pattern);
      }
    }
  }

  private Set<String> newSet(String... elements) {
    return new HashSet<String>(Arrays.asList(elements));
  }

  enum TransferKind {
    CHUNKED() {
      
      },
    FIXED_LENGTH() {
      
      },
    END_OF_STREAM() {
      
      };

    abstract void setBody(MockResponse response, byte[] content, int chunkSize) throws IOException;

    abstract void setForRequest(HttpURLConnection connection, int contentLength);

    void setBody(MockResponse response, String content, int chunkSize) throws IOException {
      setBody(response, content.getBytes("UTF-8"), chunkSize);
    }
  }

  enum ProxyConfig {
    NO_PROXY() {
      
    },

    CREATE_ARG() {
      
    },

    PROXY_SYSTEM_PROPERTY() {
      
    },

    HTTP_PROXY_SYSTEM_PROPERTY() {
      
    },

    HTTPS_PROXY_SYSTEM_PROPERTY() {
      
    }
  }

  private static class RecordingTrustManager implements X509TrustManager {
    private final List<String> calls = new ArrayList<String>();

    private String certificatesToString(X509Certificate[] certificates) {
      List<String> result = new ArrayList<String>();
      for (X509Certificate certificate : certificates) {
        result.add(certificate.getSubjectDN() + " " + certificate.getSerialNumber());
      }
      return result.toString();
    }
  }

  private static class FakeProxySelector extends ProxySelector {
    List<Proxy> proxies = new ArrayList<Proxy>();

    }

  /**
   * Tests that use this will fail unless boot classpath is set. Ex. {@code
   * -Xbootclasspath/p:/tmp/npn-boot-8.1.2.v20120308.jar}
   */
  private void enableNpn(Protocol protocol) {
    client.setSslSocketFactory(sslContext.getSocketFactory());
    client.setHostnameVerifier(new RecordingHostnameVerifier());
    client.setProtocols(Arrays.asList(protocol, Protocol.HTTP_11));
    server.useHttps(sslContext.getSocketFactory(), false);
    server.setNpnEnabled(true);
    server.setNpnProtocols(client.getProtocols());
  }
}
