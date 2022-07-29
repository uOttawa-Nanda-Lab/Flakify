package com.squareup.okhttp.internal.http;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HttpOverHttp20Draft09Test {
@Test public void acceptAndTransmitCookies() throws Exception {
  CookieManager cookieManager=new CookieManager();
  client.setCookieHandler(cookieManager);
  server.enqueue(new MockResponse().addHeader("set-cookie: c=oreo; domain=" + server.getCookieDomain()).setBody("A"));
  server.enqueue(new MockResponse().setBody("B"));
  server.play();
  URL url=server.getUrl("/");
  assertContent("A",client.open(url),Integer.MAX_VALUE);
  Map<String,List<String>> requestHeaders=Collections.emptyMap();
  assertEquals(Collections.singletonMap("Cookie",Arrays.asList("c=oreo")),cookieManager.get(url.toURI(),requestHeaders));
  assertContent("B",client.open(url),Integer.MAX_VALUE);
  RecordedRequest requestA=server.takeRequest();
  assertContainsNoneMatching(requestA.getHeaders(),"Cookie.*");
  RecordedRequest requestB=server.takeRequest();
  assertContains(requestB.getHeaders(),"cookie: c=oreo");
}

}