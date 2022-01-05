@Test public void responseCachingAndRedirects() throws Exception {
  server.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(-1,TimeUnit.HOURS)).addHeader("Expires: " + formatDate(1,TimeUnit.HOURS)).setResponseCode(HttpURLConnection.HTTP_MOVED_PERM).addHeader("Location: /foo"));
  server.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(-1,TimeUnit.HOURS)).addHeader("Expires: " + formatDate(1,TimeUnit.HOURS)).setBody("ABC"));
  server.enqueue(new MockResponse().setBody("DEF"));
  server.play();
  HttpURLConnection connection=openConnection(server.getUrl("/"));
  assertEquals("ABC",readAscii(connection));
  connection=openConnection(server.getUrl("/"));
  assertEquals("ABC",readAscii(connection));
}
