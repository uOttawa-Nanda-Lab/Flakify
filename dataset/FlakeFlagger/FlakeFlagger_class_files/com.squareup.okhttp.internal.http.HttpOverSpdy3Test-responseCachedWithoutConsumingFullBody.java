package com.squareup.okhttp.internal.http;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HttpOverSpdy3Test {
@Test public void responseCachedWithoutConsumingFullBody() throws IOException {
  client.setOkResponseCache(cache);
  server.enqueue(new MockResponse().addHeader("cache-control: max-age=60").setBody("ABCD"));
  server.enqueue(new MockResponse().addHeader("cache-control: max-age=60").setBody("EFGH"));
  server.play();
  HttpURLConnection connection1=client.open(server.getUrl("/"));
  InputStream in1=connection1.getInputStream();
  assertEquals("AB",readAscii(in1,2));
  in1.close();
  HttpURLConnection connection2=client.open(server.getUrl("/"));
  InputStream in2=connection2.getInputStream();
  assertEquals("ABCD",readAscii(in2,Integer.MAX_VALUE));
  in2.close();
}

}