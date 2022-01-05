@Test public void emptyResponseHeaderNameFromCacheIsLenient() throws Exception {
  server.enqueue(new MockResponse().addHeader("Cache-Control: max-age=120").addHeader(": A").setBody("body"));
  server.play();
  HttpURLConnection connection=openConnection(server.getUrl("/"));
  assertEquals("A",connection.getHeaderField(""));
}
