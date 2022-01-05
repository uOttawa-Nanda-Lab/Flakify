@Test public void cannotSetNegativeFixedLengthStreamingMode() throws Exception {
  server.play();
  connection=client.open(server.getUrl("/"));
  try {
    connection.setFixedLengthStreamingMode(-2);
    fail();
  }
 catch (  IllegalArgumentException expected) {
  }
}
