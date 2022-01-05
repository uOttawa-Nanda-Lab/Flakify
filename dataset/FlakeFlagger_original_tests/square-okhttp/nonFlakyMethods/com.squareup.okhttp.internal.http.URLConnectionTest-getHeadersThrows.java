@Test public void getHeadersThrows() throws IOException {
  for (  InetAddress inetAddress : InetAddress.getAllByName(server.getHostName())) {
    server.enqueue(new MockResponse().setSocketPolicy(DISCONNECT_AT_START));
  }
  server.play();
  connection=client.open(server.getUrl("/"));
  try {
    connection.getInputStream();
    fail();
  }
 catch (  IOException expected) {
  }
  try {
    connection.getInputStream();
    fail();
  }
 catch (  IOException expected) {
  }
}
