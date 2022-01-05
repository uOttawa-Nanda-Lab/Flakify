@Test public void doesNotFollow21Redirects() throws Exception {
  for (int i=0; i < 21; i++) {
    server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_MOVED_TEMP).addHeader("Location: /" + (i + 1)).setBody("Redirecting to /" + (i + 1)));
  }
  server.play();
  connection=client.open(server.getUrl("/0"));
  try {
    connection.getInputStream();
    fail();
  }
 catch (  ProtocolException expected) {
    assertEquals(HttpURLConnection.HTTP_MOVED_TEMP,connection.getResponseCode());
    assertEquals("Too many redirects: 21",expected.getMessage());
    assertContent("Redirecting to /21",connection);
    assertEquals(server.getUrl("/20"),connection.getURL());
  }
}
