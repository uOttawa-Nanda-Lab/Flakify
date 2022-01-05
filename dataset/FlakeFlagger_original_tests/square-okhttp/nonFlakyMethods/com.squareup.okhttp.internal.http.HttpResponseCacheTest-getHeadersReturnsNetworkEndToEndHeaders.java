@Test public void getHeadersReturnsNetworkEndToEndHeaders() throws Exception {
  server.enqueue(new MockResponse().addHeader("Allow: GET, HEAD").addHeader("Last-Modified: " + formatDate(-1,TimeUnit.HOURS)).addHeader("Cache-Control: max-age=0").setBody("A"));
  server.enqueue(new MockResponse().addHeader("Allow: GET, HEAD, PUT").setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED));
  server.play();
  URLConnection connection1=openConnection(server.getUrl("/"));
  assertEquals("A",readAscii(connection1));
  assertEquals("GET, HEAD",connection1.getHeaderField("Allow"));
  URLConnection connection2=openConnection(server.getUrl("/"));
  assertEquals("A",readAscii(connection2));
  assertEquals("GET, HEAD, PUT",connection2.getHeaderField("Allow"));
}
