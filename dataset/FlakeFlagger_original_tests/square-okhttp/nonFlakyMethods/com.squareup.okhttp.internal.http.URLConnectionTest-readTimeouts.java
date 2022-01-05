@Test public void readTimeouts() throws IOException {
  MockResponse timeout=new MockResponse().setBody("ABC").clearHeaders().addHeader("Content-Length: 4");
  server.enqueue(timeout);
  server.enqueue(new MockResponse().setBody("unused"));
  server.play();
  URLConnection connection=client.open(server.getUrl("/"));
  connection.setReadTimeout(1000);
  InputStream in=connection.getInputStream();
  assertEquals('A',in.read());
  assertEquals('B',in.read());
  assertEquals('C',in.read());
  try {
    in.read();
    fail();
  }
 catch (  SocketTimeoutException expected) {
  }
}
