@Test public void missingChunkBody() throws IOException {
  server.enqueue(new MockResponse().setBody("5").clearHeaders().addHeader("Transfer-encoding: chunked").setSocketPolicy(DISCONNECT_AT_END));
  server.play();
  URLConnection connection=client.open(server.getUrl("/"));
  try {
    readAscii(connection.getInputStream(),Integer.MAX_VALUE);
    fail();
  }
 catch (  IOException e) {
  }
}
