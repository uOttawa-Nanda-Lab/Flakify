package com.squareup.okhttp.internal.http;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HttpOverHttp20Draft09Test {
@Test public void emptyResponse() throws IOException {
  server.enqueue(new MockResponse());
  server.play();
  connection=client.open(server.getUrl("/foo"));
  assertEquals(-1,connection.getInputStream().read());
}

}