@Test public void getContentTypeConnects() throws Exception {
  server.enqueue(new MockResponse().addHeader("Content-Type: text/plain").setBody("ABC"));
  server.play();
  connection=client.open(server.getUrl("/"));
  assertEquals("text/plain",connection.getContentType());
}
