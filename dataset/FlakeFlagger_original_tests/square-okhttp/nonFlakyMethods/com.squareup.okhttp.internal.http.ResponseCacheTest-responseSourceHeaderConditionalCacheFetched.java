@Test public void responseSourceHeaderConditionalCacheFetched() throws IOException {
  server.enqueue(new MockResponse().setBody("A").addHeader("Cache-Control: max-age=30").addHeader("Date: " + formatDate(-31,TimeUnit.MINUTES)));
  server.enqueue(new MockResponse().setBody("B").addHeader("Cache-Control: max-age=30").addHeader("Date: " + formatDate(0,TimeUnit.MINUTES)));
  server.play();
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  HttpURLConnection connection=openConnection(server.getUrl("/"));
  assertEquals("B",readAscii(connection));
  String source=connection.getHeaderField(OkHeaders.RESPONSE_SOURCE);
  assertEquals(ResponseSource.CONDITIONAL_CACHE + " 200",source);
}
