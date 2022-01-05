@Test public void emptyRequestHeaderNameIsStrict() throws Exception {
  server.enqueue(new MockResponse().setBody("body"));
  server.play();
  connection=client.open(server.getUrl("/"));
  try {
    connection.setRequestProperty("","A");
    fail();
  }
 catch (  IllegalArgumentException expected) {
  }
}
