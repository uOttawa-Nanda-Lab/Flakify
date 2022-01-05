@Test public void canSetNegativeChunkedStreamingMode() throws Exception {
  server.play();
  connection=client.open(server.getUrl("/"));
  connection.setChunkedStreamingMode(-2);
}
