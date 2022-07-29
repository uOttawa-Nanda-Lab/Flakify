package com.squareup.okhttp.internal.http;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HttpOverHttp20Draft09Test {
@Test public void conditionalCache() throws IOException {
  client.setOkResponseCache(cache);
  server.enqueue(new MockResponse().addHeader("ETag: v1").setBody("A"));
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED));
  server.play();
  assertContent("A",client.open(server.getUrl("/")),Integer.MAX_VALUE);
  assertEquals(1,cache.getRequestCount());
  assertEquals(1,cache.getNetworkCount());
  assertEquals(0,cache.getHitCount());
  assertContent("A",client.open(server.getUrl("/")),Integer.MAX_VALUE);
  assertEquals(2,cache.getRequestCount());
  assertEquals(2,cache.getNetworkCount());
  assertEquals(1,cache.getHitCount());
}

}