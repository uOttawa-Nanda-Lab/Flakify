@Test public void redirectedFromHttpToHttpsFollowingProtocolRedirects() throws Exception {
  server2=new MockWebServer();
  server2.useHttps(sslContext.getSocketFactory(),false);
  server2.enqueue(new MockResponse().setBody("This is secure HTTPS!"));
  server2.play();
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_MOVED_TEMP).addHeader("Location: " + server2.getUrl("/")).setBody("This page has moved!"));
  server.play();
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(new RecordingHostnameVerifier());
  client.setFollowProtocolRedirects(true);
  connection=client.open(server.getUrl("/"));
  assertContent("This is secure HTTPS!",connection);
  assertFalse(connection instanceof HttpsURLConnection);
}
