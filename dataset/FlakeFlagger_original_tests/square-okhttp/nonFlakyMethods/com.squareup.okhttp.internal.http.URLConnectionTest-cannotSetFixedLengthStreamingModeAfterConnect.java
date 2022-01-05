@Test public void cannotSetFixedLengthStreamingModeAfterConnect() throws Exception {
  server.enqueue(new MockResponse().setBody("A"));
  server.play();
  connection=client.open(server.getUrl("/"));
  assertEquals("A",readAscii(connection.getInputStream(),Integer.MAX_VALUE));
  try {
    connection.setFixedLengthStreamingMode(1);
    fail();
  }
 catch (  IllegalStateException expected) {
  }
}
