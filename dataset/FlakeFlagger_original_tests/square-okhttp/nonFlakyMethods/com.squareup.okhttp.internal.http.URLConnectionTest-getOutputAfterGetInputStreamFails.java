@Test public void getOutputAfterGetInputStreamFails() throws Exception {
  server.enqueue(new MockResponse());
  server.play();
  connection=client.open(server.getUrl("/"));
  connection.setDoOutput(true);
  try {
    connection.getInputStream();
    connection.getOutputStream();
    fail();
  }
 catch (  ProtocolException expected) {
  }
}
