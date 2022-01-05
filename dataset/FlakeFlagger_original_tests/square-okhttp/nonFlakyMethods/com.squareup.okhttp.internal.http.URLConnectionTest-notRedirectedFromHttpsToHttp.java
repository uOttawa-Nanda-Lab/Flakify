@Test public void notRedirectedFromHttpsToHttp() throws IOException, InterruptedException {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_MOVED_TEMP).addHeader("Location: http://anyhost/foo").setBody("This page has moved!"));
  server.play();
  client.setFollowProtocolRedirects(false);
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(new RecordingHostnameVerifier());
  connection=client.open(server.getUrl("/"));
  assertEquals("This page has moved!",readAscii(connection.getInputStream(),Integer.MAX_VALUE));
}
