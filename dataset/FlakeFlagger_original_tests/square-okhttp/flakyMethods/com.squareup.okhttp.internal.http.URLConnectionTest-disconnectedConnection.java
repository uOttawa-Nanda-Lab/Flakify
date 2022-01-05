@Test public void disconnectedConnection() throws IOException {
  server.enqueue(new MockResponse().setBody("ABCDEFGHIJKLMNOPQR"));
  server.play();
  connection=client.open(server.getUrl("/"));
  InputStream in=connection.getInputStream();
  assertEquals('A',(char)in.read());
  connection.disconnect();
  try {
    in.read();
    fail("Expected a connection closed exception");
  }
 catch (  IOException expected) {
  }
}
