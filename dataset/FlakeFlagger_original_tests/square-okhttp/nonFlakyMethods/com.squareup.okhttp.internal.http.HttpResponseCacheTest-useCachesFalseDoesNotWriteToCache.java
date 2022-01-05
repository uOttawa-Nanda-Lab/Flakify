@Test public void useCachesFalseDoesNotWriteToCache() throws Exception {
  server.enqueue(new MockResponse().addHeader("Cache-Control: max-age=60").setBody("A").setBody("A"));
  server.enqueue(new MockResponse().setBody("B"));
  server.play();
  URLConnection connection=openConnection(server.getUrl("/"));
  connection.setUseCaches(false);
  assertEquals("A",readAscii(connection));
  assertEquals("B",readAscii(openConnection(server.getUrl("/"))));
}
