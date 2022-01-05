@Test public void setDoOutputOrDoInputAfterConnectFails() throws Exception {
  server.enqueue(new MockResponse());
  server.play();
  connection=client.open(server.getUrl("/"));
  connection.connect();
  try {
    connection.setDoOutput(true);
    fail();
  }
 catch (  IllegalStateException expected) {
  }
  try {
    connection.setDoInput(true);
    fail();
  }
 catch (  IllegalStateException expected) {
  }
}
