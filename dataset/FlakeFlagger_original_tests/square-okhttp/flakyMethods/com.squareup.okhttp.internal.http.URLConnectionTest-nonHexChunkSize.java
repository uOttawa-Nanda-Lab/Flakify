@Test public void nonHexChunkSize() throws IOException {
  server.enqueue(new MockResponse().setBody("5\r\nABCDE\r\nG\r\nFGHIJKLMNOPQRSTU\r\n0\r\n\r\n").clearHeaders().addHeader("Transfer-encoding: chunked"));
  server.play();
  URLConnection connection=client.open(server.getUrl("/"));
  try {
    readAscii(connection.getInputStream(),Integer.MAX_VALUE);
    fail();
  }
 catch (  IOException e) {
  }
}
