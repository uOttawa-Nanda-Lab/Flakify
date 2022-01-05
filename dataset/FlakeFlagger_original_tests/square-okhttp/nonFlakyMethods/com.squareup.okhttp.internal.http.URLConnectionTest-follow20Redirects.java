@Test public void follow20Redirects() throws Exception {
  for (int i=0; i < 20; i++) {
    server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_MOVED_TEMP).addHeader("Location: /" + (i + 1)).setBody("Redirecting to /" + (i + 1)));
  }
  server.enqueue(new MockResponse().setBody("Success!"));
  server.play();
  connection=client.open(server.getUrl("/0"));
  assertContent("Success!",connection);
  assertEquals(server.getUrl("/20"),connection.getURL());
}
