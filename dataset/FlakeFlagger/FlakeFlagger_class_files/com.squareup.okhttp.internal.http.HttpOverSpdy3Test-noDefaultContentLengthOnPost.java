package com.squareup.okhttp.internal.http;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HttpOverSpdy3Test {
/** 
 * An output stream can be written to more than once, so we can't guess content length. 
 */
@Test public void noDefaultContentLengthOnPost() throws Exception {
  MockResponse response=new MockResponse().setBody("ABCDE");
  server.enqueue(response);
  server.play();
  connection=client.open(server.getUrl("/foo"));
  connection.setDoOutput(true);
  connection.getOutputStream().write(postBytes);
  assertContent("ABCDE",connection,Integer.MAX_VALUE);
  RecordedRequest request=server.takeRequest();
  assertEquals("POST /foo HTTP/1.1",request.getRequestLine());
  assertArrayEquals(postBytes,request.getBody());
  assertNull(request.getHeader("Content-Length"));
}

}