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
import com.squareup.okhttp.internal.SslContextBuilder;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.HttpURLConnection;
import java.net.ResponseCache;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * A white-box test for {@link ResponseCacheAdapter}. See also {@link ResponseCacheTest} for
 * black-box tests that check that {@link ResponseCache} classes are called correctly by OkHttp.
 */
public class ResponseCacheAdapterTest {

  private static final SSLContext sslContext = SslContextBuilder.localhost();
  private static final HostnameVerifier NULL_HOSTNAME_VERIFIER = new HostnameVerifier() {
    public boolean verify(String hostname, SSLSession session) {
      return true;
    }
  };

  private MockWebServer server;

  private OkHttpClient client;

  private HttpURLConnection connection;

  @Before
  public void setUp() throws Exception {
    server = new MockWebServer();
    client = new OkHttpClient();
  }

  @Test public void get_allParameters() throws Exception{final URL serverUrl=configureServer(new MockResponse());assertEquals("http",serverUrl.getProtocol());ResponseCache responseCache=new NoOpResponseCache(){@Override public CacheResponse get(URI uri,String method,Map<String, List<String>> headers) throws IOException{assertEquals(toUri(serverUrl),uri);assertEquals("GET",method);assertTrue("Arbitrary standard header not present",headers.containsKey("User-Agent"));assertEquals(Collections.singletonList("value1"),headers.get("key1"));return null;}};client.setResponseCache(responseCache);connection=client.open(serverUrl);connection.setRequestProperty("key1","value1");executeGet(connection);}

  private static void assertResponseHeaderAtIndex(HttpURLConnection httpUrlConnection, int headerIndex,
      String expectedKey, String expectedValue) {
    assertEquals(expectedKey, httpUrlConnection.getHeaderFieldKey(headerIndex));
    assertEquals(expectedValue, httpUrlConnection.getHeaderField(headerIndex));

  }

  private void assertHeadersContainsMapping(Map<String, List<String>> headers, String expectedKey,
      String... expectedValues) {
    assertTrue(headers.containsKey(expectedKey));
    assertEquals(newSet(expectedValues), new HashSet<String>(headers.get(expectedKey)));
  }

  private void executeGet(HttpURLConnection connection) throws IOException {
    connection.connect();
    connection.getHeaderFields();
    connection.disconnect();
  }

  private void executePost(HttpURLConnection connection) throws IOException {
    connection.setDoOutput(true);
    connection.connect();
    connection.getOutputStream().write("Hello World".getBytes());
    connection.disconnect();
  }

  private URL configureServer(MockResponse mockResponse) throws Exception {
    server.enqueue(mockResponse);
    server.play();
    return server.getUrl("/");
  }

  private URL configureHttpsServer(MockResponse mockResponse) throws Exception {
    server.useHttps(sslContext.getSocketFactory(), false /* tunnelProxy */);
    server.enqueue(mockResponse);
    server.play();
    return server.getUrl("/");
  }

  private static class NoOpResponseCache extends ResponseCache {

    @Override
    public CacheResponse get(URI uri, String s, Map<String, List<String>> stringListMap)
        throws IOException {
      return null;
    }

    @Override
    public CacheRequest put(URI uri, URLConnection urlConnection) throws IOException {
      return null;
    }
  }

  private static URI toUri(URL serverUrl) {
    try {
      return serverUrl.toURI();
    } catch (URISyntaxException e) {
      fail(e.getMessage());
      return null;
    }
  }

  private static Set<String> newSet(String... elements) {
    return new HashSet<String>(Arrays.asList(elements));
  }
}
