@Test public void serverSendsUnnecessaryWhitespace() throws Exception {
  server.enqueue(new MockResponse().setStatus(" HTTP/1.1 2147483648 OK"));
  server.play();
  connection=client.open(server.getUrl("/"));
  try {
    connection.getResponseCode();
    fail();
  }
 catch (  IOException expected) {
  }
}
