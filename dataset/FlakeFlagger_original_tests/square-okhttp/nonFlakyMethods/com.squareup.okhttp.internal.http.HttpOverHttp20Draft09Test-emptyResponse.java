@Test public void emptyResponse() throws IOException {
  server.enqueue(new MockResponse());
  server.play();
  connection=client.open(server.getUrl("/foo"));
  assertEquals(-1,connection.getInputStream().read());
}
