@Test public void getOutputStreamOnGetFails() throws Exception {
  server.enqueue(new MockResponse());
  server.play();
  connection=client.open(server.getUrl("/"));
  try {
    connection.getOutputStream();
    fail();
  }
 catch (  ProtocolException expected) {
  }
}
