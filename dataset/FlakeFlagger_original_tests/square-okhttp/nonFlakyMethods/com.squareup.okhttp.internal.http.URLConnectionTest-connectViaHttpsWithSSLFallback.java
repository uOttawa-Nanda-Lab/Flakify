@Test public void connectViaHttpsWithSSLFallback() throws IOException, InterruptedException {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.FAIL_HANDSHAKE));
  server.enqueue(new MockResponse().setBody("this response comes via SSL"));
  server.play();
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(new RecordingHostnameVerifier());
  connection=client.open(server.getUrl("/foo"));
  assertContent("this response comes via SSL",connection);
  RecordedRequest request=server.takeRequest();
  assertEquals("GET /foo HTTP/1.1",request.getRequestLine());
}
