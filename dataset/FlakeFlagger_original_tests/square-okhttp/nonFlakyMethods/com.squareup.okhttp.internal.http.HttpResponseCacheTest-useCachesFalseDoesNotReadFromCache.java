@Test public void useCachesFalseDoesNotReadFromCache() throws Exception {
  server.enqueue(new MockResponse().addHeader("Cache-Control: max-age=60").setBody("A").setBody("A"));
  server.enqueue(new MockResponse().setBody("B"));
  server.play();
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  URLConnection connection=openConnection(server.getUrl("/"));
  connection.setUseCaches(false);
  assertEquals("B",readAscii(connection));
}
