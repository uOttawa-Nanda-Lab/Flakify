@Test public void requestMinFresh() throws IOException {
  server.enqueue(new MockResponse().setBody("A").addHeader("Cache-Control: max-age=60").addHeader("Date: " + formatDate(0,TimeUnit.MINUTES)));
  server.enqueue(new MockResponse().setBody("B"));
  server.play();
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  URLConnection connection=openConnection(server.getUrl("/"));
  connection.addRequestProperty("Cache-Control","min-fresh=120");
  assertEquals("B",readAscii(connection));
}
