package com.squareup.okhttp.internal.http;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HttpOverHttp20Draft09Test {
@Test public void userSuppliedContentLengthHeader() throws Exception {
  MockResponse response=new MockResponse().setBody("ABCDE");
  server.enqueue(response);
  server.play();
  connection=client.open(server.getUrl("/foo"));
  connection.setRequestProperty("Content-Length",String.valueOf(postBytes.length));
  connection.setDoOutput(true);
  connection.getOutputStream().write(postBytes);
  assertContent("ABCDE",connection,Integer.MAX_VALUE);
  RecordedRequest request=server.takeRequest();
  assertEquals("POST /foo HTTP/1.1",request.getRequestLine());
  assertArrayEquals(postBytes,request.getBody());
  assertEquals(postBytes.length,Integer.parseInt(request.getHeader("Content-Length")));
}

}