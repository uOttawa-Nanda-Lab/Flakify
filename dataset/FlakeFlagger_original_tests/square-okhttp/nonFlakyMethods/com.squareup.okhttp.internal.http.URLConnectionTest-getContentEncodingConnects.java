@Test public void getContentEncodingConnects() throws Exception {
  server.enqueue(new MockResponse().addHeader("Content-Encoding: identity").setBody("ABC"));
  server.play();
  connection=client.open(server.getUrl("/"));
  assertEquals("identity",connection.getContentEncoding());
}
