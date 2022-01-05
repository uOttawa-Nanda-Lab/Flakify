@Test public void requestMaxStaleNotHonoredWithMustRevalidate() throws IOException {
  server.enqueue(new MockResponse().setBody("A").addHeader("Cache-Control: max-age=120, must-revalidate").addHeader("Date: " + formatDate(-4,TimeUnit.MINUTES)));
  server.enqueue(new MockResponse().setBody("B"));
  server.play();
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  URLConnection connection=openConnection(server.getUrl("/"));
  connection.addRequestProperty("Cache-Control","max-stale=180");
  assertEquals("B",readAscii(connection));
}
