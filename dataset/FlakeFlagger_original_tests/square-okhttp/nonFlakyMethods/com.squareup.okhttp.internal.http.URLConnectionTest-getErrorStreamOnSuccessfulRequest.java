@Test public void getErrorStreamOnSuccessfulRequest() throws Exception {
  server.enqueue(new MockResponse().setBody("A"));
  server.play();
  connection=client.open(server.getUrl("/"));
  assertNull(connection.getErrorStream());
}
