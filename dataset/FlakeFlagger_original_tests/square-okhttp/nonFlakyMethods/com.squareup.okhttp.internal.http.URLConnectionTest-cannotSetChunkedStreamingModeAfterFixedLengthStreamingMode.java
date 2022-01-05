@Test public void cannotSetChunkedStreamingModeAfterFixedLengthStreamingMode() throws Exception {
  server.play();
  connection=client.open(server.getUrl("/"));
  connection.setFixedLengthStreamingMode(1);
  try {
    connection.setChunkedStreamingMode(1);
    fail();
  }
 catch (  IllegalStateException expected) {
  }
}
