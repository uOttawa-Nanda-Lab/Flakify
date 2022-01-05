@Test public void tls() throws Exception {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().setBody("abc").addHeader("Content-Type: text/plain"));
  server.play();
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(new RecordingHostnameVerifier());
  Request request=new Request.Builder().url(server.getUrl("/")).build();
  client.enqueue(request,receiver);
  receiver.await(request.url()).assertHandshake();
}
