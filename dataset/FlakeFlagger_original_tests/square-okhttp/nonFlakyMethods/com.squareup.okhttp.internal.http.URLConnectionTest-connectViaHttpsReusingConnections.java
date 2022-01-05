@Test public void connectViaHttpsReusingConnections() throws IOException, InterruptedException {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().setBody("this response comes via HTTPS"));
  server.enqueue(new MockResponse().setBody("another response via HTTPS"));
  server.play();
  SSLSocketFactory clientSocketFactory=sslContext.getSocketFactory();
  RecordingHostnameVerifier hostnameVerifier=new RecordingHostnameVerifier();
  client.setSslSocketFactory(clientSocketFactory);
  client.setHostnameVerifier(hostnameVerifier);
  connection=client.open(server.getUrl("/"));
  assertContent("this response comes via HTTPS",connection);
  connection=client.open(server.getUrl("/"));
  assertContent("another response via HTTPS",connection);
  assertEquals(0,server.takeRequest().getSequenceNumber());
  assertEquals(1,server.takeRequest().getSequenceNumber());
}
