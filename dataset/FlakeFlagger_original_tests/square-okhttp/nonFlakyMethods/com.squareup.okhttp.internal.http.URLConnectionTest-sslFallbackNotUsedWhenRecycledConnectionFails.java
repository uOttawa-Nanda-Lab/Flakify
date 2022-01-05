/** 
 * When a pooled connection fails, don't blame the route. Otherwise pooled connection failures can cause unnecessary SSL fallbacks. https://github.com/square/okhttp/issues/515
 */
@Test public void sslFallbackNotUsedWhenRecycledConnectionFails() throws Exception {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().setBody("abc").setSocketPolicy(SocketPolicy.DISCONNECT_AT_END));
  server.enqueue(new MockResponse().setBody("def"));
  server.play();
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(new RecordingHostnameVerifier());
  assertContent("abc",client.open(server.getUrl("/")));
  assertContent("def",client.open(server.getUrl("/")));
  RecordedRequest request1=server.takeRequest();
  assertEquals("TLSv1",request1.getSslProtocol());
  RecordedRequest request2=server.takeRequest();
  assertEquals("TLSv1",request2.getSslProtocol());
}
