@Test public void emptyResponseHeaderNameFromCacheIsLenient() throws Exception {
  server.enqueue(new MockResponse().addHeader("Cache-Control: max-age=120").addHeader(": A").setBody("body"));
  server.play();
  HttpURLConnection connection=client.open(server.getUrl("/"));
  assertEquals("A",connection.getHeaderField(""));
}
