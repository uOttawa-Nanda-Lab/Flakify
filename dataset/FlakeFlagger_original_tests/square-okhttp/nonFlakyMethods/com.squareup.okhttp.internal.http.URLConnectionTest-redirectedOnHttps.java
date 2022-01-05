@Test public void redirectedOnHttps() throws IOException, InterruptedException {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_MOVED_TEMP).addHeader("Location: /foo").setBody("This page has moved!"));
  server.enqueue(new MockResponse().setBody("This is the new location!"));
  server.play();
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(new RecordingHostnameVerifier());
  connection=client.open(server.getUrl("/"));
  assertEquals("This is the new location!",readAscii(connection.getInputStream(),Integer.MAX_VALUE));
  RecordedRequest first=server.takeRequest();
  assertEquals("GET / HTTP/1.1",first.getRequestLine());
  RecordedRequest retry=server.takeRequest();
  assertEquals("GET /foo HTTP/1.1",retry.getRequestLine());
  assertEquals("Expected connection reuse",1,retry.getSequenceNumber());
}
