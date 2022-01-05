@Test public void notRedirectedFromHttpToHttps() throws IOException, InterruptedException {
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_MOVED_TEMP).addHeader("Location: https://anyhost/foo").setBody("This page has moved!"));
  server.play();
  client.setFollowProtocolRedirects(false);
  connection=client.open(server.getUrl("/"));
  assertEquals("This page has moved!",readAscii(connection.getInputStream(),Integer.MAX_VALUE));
}
