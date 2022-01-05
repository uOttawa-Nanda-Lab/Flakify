@Test public void connectViaHttpsReusingConnectionsDifferentFactories() throws IOException, InterruptedException {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().setBody("this response comes via HTTPS"));
  server.enqueue(new MockResponse().setBody("another response via HTTPS"));
  server.play();
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(new RecordingHostnameVerifier());
  HttpURLConnection connection1=client.open(server.getUrl("/"));
  assertContent("this response comes via HTTPS",connection1);
  client.setSslSocketFactory(null);
  HttpURLConnection connection2=client.open(server.getUrl("/"));
  try {
    readAscii(connection2.getInputStream(),Integer.MAX_VALUE);
    fail("without an SSL socket factory, the connection should fail");
  }
 catch (  SSLException expected) {
  }
}
