@Test public void connectViaHttps() throws Exception {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().setBody("this response comes via HTTPS"));
  server.play();
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(new RecordingHostnameVerifier());
  connection=client.open(server.getUrl("/foo"));
  assertContent("this response comes via HTTPS",connection);
  RecordedRequest request=server.takeRequest();
  assertEquals("GET /foo HTTP/1.1",request.getRequestLine());
}
