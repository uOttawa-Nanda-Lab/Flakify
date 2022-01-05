@Test public void connectRetriesUntilConnectedOrFailed() throws Exception {
  server.play();
  URL url=server.getUrl("/foo");
  server.shutdown();
  connection=client.open(url);
  try {
    connection.connect();
    fail();
  }
 catch (  IOException expected) {
  }
}
