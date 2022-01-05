@Test public void requestMaxAge() throws IOException {
  server.enqueue(new MockResponse().setBody("A").addHeader("Last-Modified: " + formatDate(-2,TimeUnit.HOURS)).addHeader("Date: " + formatDate(-1,TimeUnit.MINUTES)).addHeader("Expires: " + formatDate(1,TimeUnit.HOURS)));
  server.enqueue(new MockResponse().setBody("B"));
  server.play();
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  URLConnection connection=openConnection(server.getUrl("/"));
  connection.addRequestProperty("Cache-Control","max-age=30");
  assertEquals("B",readAscii(connection));
}
