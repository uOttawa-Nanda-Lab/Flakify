@Test public void emptyResponseHeaderNameIsLenient() throws Exception {
  server.enqueue(new MockResponse().addHeader(":A").setBody("body"));
  server.play();
  connection=client.open(server.getUrl("/"));
  connection.getResponseCode();
  assertEquals("A",connection.getHeaderField(""));
}
