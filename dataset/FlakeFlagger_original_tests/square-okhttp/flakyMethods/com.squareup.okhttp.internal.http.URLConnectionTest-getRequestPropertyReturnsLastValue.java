@Test public void getRequestPropertyReturnsLastValue() throws Exception {
  server.play();
  connection=client.open(server.getUrl("/"));
  connection.addRequestProperty("A","value1");
  connection.addRequestProperty("A","value2");
  assertEquals("value2",connection.getRequestProperty("A"));
}
