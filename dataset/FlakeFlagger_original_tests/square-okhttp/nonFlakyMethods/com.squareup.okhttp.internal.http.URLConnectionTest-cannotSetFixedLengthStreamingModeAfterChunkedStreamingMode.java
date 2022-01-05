@Test public void cannotSetFixedLengthStreamingModeAfterChunkedStreamingMode() throws Exception {
  server.play();
  connection=client.open(server.getUrl("/"));
  connection.setChunkedStreamingMode(1);
  try {
    connection.setFixedLengthStreamingMode(1);
    fail();
  }
 catch (  IllegalStateException expected) {
  }
}
