@Test public void emptyRequestHeaderValueIsAllowed() throws Exception {
  server.enqueue(new MockResponse().setBody("body"));
  server.play();
  connection=client.open(server.getUrl("/"));
  connection.addRequestProperty("B","");
  assertContent("body",connection);
  assertEquals("",connection.getRequestProperty("B"));
}
