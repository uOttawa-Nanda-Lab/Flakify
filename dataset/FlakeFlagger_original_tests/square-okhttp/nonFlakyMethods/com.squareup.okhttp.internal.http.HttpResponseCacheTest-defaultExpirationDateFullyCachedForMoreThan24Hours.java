@Test public void defaultExpirationDateFullyCachedForMoreThan24Hours() throws Exception {
  server.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(-105,TimeUnit.DAYS)).addHeader("Date: " + formatDate(-5,TimeUnit.DAYS)).setBody("A"));
  server.play();
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  URLConnection connection=openConnection(server.getUrl("/"));
  assertEquals("A",readAscii(connection));
  assertEquals("113 HttpURLConnection \"Heuristic expiration\"",connection.getHeaderField("Warning"));
}
