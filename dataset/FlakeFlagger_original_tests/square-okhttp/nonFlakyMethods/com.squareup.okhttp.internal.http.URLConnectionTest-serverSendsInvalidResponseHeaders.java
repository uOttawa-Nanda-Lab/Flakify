@Test public void serverSendsInvalidResponseHeaders() throws Exception {
  server.enqueue(new MockResponse().setStatus("HTP/1.1 200 OK"));
  server.play();
  connection=client.open(server.getUrl("/"));
  try {
    connection.getResponseCode();
    fail();
  }
 catch (  IOException expected) {
  }
}
