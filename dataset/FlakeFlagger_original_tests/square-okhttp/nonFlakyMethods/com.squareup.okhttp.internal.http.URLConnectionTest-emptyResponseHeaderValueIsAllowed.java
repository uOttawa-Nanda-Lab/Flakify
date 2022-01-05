@Test public void emptyResponseHeaderValueIsAllowed() throws Exception {
  server.enqueue(new MockResponse().addHeader("A:").setBody("body"));
  server.play();
  connection=client.open(server.getUrl("/"));
  assertContent("body",connection);
  assertEquals("",connection.getHeaderField("A"));
}
