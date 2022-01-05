@Test public void disconnectBeforeConnect() throws IOException {
  server.enqueue(new MockResponse().setBody("A"));
  server.play();
  connection=client.open(server.getUrl("/"));
  connection.disconnect();
  assertContent("A",connection);
  assertEquals(200,connection.getResponseCode());
}
