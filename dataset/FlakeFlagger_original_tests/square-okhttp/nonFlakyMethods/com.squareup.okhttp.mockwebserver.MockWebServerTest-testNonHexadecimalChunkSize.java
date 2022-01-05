public void testNonHexadecimalChunkSize() throws Exception {
  server.enqueue(new MockResponse().setBody("G\r\nxxxxxxxxxxxxxxxx\r\n0\r\n\r\n").clearHeaders().addHeader("Transfer-encoding: chunked"));
  server.play();
  URLConnection connection=server.getUrl("/").openConnection();
  InputStream in=connection.getInputStream();
  try {
    in.read();
    fail();
  }
 catch (  IOException expected) {
  }
}
