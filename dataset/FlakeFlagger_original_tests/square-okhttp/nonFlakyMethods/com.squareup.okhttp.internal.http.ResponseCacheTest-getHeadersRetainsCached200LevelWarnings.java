@Test public void getHeadersRetainsCached200LevelWarnings() throws Exception {
  server.enqueue(new MockResponse().addHeader("Warning: 299 test danger").addHeader("Last-Modified: " + formatDate(-1,TimeUnit.HOURS)).addHeader("Cache-Control: max-age=0").setBody("A"));
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED));
  server.play();
  URLConnection connection1=openConnection(server.getUrl("/"));
  assertEquals("A",readAscii(connection1));
  assertEquals("299 test danger",connection1.getHeaderField("Warning"));
  URLConnection connection2=openConnection(server.getUrl("/"));
  assertEquals("A",readAscii(connection2));
  assertEquals("299 test danger",connection2.getHeaderField("Warning"));
}
