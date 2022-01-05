@Test public void getErrorStreamOnUnsuccessfulRequest() throws Exception {
  server.enqueue(new MockResponse().setResponseCode(404).setBody("A"));
  server.play();
  connection=client.open(server.getUrl("/"));
  assertEquals("A",readAscii(connection.getErrorStream(),Integer.MAX_VALUE));
}
