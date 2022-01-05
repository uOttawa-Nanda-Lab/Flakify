@Test public void tls() throws Exception {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().setBody("abc").addHeader("Content-Type: text/plain"));
  server.play();
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(new RecordingHostnameVerifier());
  onSuccess(new Request.Builder().url(server.getUrl("/")).build()).assertHandshake();
}
