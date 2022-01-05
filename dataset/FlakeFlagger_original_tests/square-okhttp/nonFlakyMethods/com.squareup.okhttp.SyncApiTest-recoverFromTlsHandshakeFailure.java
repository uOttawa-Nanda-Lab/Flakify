@Test public void recoverFromTlsHandshakeFailure() throws Exception {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.FAIL_HANDSHAKE));
  server.enqueue(new MockResponse().setBody("abc"));
  server.play();
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(new RecordingHostnameVerifier());
  onSuccess(new Request.Builder().url(server.getUrl("/")).build()).assertBody("abc");
}
