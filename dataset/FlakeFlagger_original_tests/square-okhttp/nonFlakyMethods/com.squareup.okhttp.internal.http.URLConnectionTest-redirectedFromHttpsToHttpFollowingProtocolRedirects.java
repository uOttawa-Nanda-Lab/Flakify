@Test public void redirectedFromHttpsToHttpFollowingProtocolRedirects() throws Exception {
  server2=new MockWebServer();
  server2.enqueue(new MockResponse().setBody("This is insecure HTTP!"));
  server2.play();
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_MOVED_TEMP).addHeader("Location: " + server2.getUrl("/")).setBody("This page has moved!"));
  server.play();
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(new RecordingHostnameVerifier());
  client.setFollowProtocolRedirects(true);
  HttpsURLConnection connection=(HttpsURLConnection)client.open(server.getUrl("/"));
  assertContent("This is insecure HTTP!",connection);
  assertNull(connection.getCipherSuite());
  assertNull(connection.getLocalCertificates());
  assertNull(connection.getServerCertificates());
  assertNull(connection.getPeerPrincipal());
  assertNull(connection.getLocalPrincipal());
}
