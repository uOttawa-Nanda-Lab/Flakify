@Test public void getHeadersReturnsCachedHopByHopHeaders() throws Exception {
  server.enqueue(new MockResponse().addHeader("Transfer-Encoding: identity").addHeader("Last-Modified: " + formatDate(-1,TimeUnit.HOURS)).addHeader("Cache-Control: max-age=0").setBody("A"));
  server.enqueue(new MockResponse().addHeader("Transfer-Encoding: none").setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED));
  server.play();
  URLConnection connection1=openConnection(server.getUrl("/"));
  assertEquals("A",readAscii(connection1));
  assertEquals("identity",connection1.getHeaderField("Transfer-Encoding"));
  URLConnection connection2=openConnection(server.getUrl("/"));
  assertEquals("A",readAscii(connection2));
  assertEquals("identity",connection2.getHeaderField("Transfer-Encoding"));
}
