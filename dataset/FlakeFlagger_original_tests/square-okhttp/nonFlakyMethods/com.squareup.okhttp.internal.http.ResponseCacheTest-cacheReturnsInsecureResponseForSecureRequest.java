@Test public void cacheReturnsInsecureResponseForSecureRequest() throws IOException {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().setBody("ABC"));
  server.enqueue(new MockResponse().setBody("DEF"));
  server.play();
  client.setResponseCache(new InsecureResponseCache(new InMemoryResponseCache()));
  HttpsURLConnection connection1=(HttpsURLConnection)openConnection(server.getUrl("/"));
  connection1.setSSLSocketFactory(sslContext.getSocketFactory());
  connection1.setHostnameVerifier(NULL_HOSTNAME_VERIFIER);
  assertEquals("ABC",readAscii(connection1));
  HttpsURLConnection connection2=(HttpsURLConnection)openConnection(server.getUrl("/"));
  connection2.setSSLSocketFactory(sslContext.getSocketFactory());
  connection2.setHostnameVerifier(NULL_HOSTNAME_VERIFIER);
  assertEquals("DEF",readAscii(connection2));
}
