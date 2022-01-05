@Test public void serverSendsInvalidCodeNotANumber() throws Exception {
  server.enqueue(new MockResponse().setStatus("HTTP/1.1 00a OK"));
  server.play();
  connection=client.open(server.getUrl("/"));
  try {
    connection.getResponseCode();
    fail();
  }
 catch (  IOException expected) {
  }
}
