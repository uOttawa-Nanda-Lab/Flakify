@Test public void getContent() throws Exception {
  server.enqueue(new MockResponse().addHeader("Content-Type: text/plain").setBody("A"));
  server.play();
  connection=client.open(server.getUrl("/"));
  InputStream in=(InputStream)connection.getContent();
  assertEquals("A",readAscii(in,Integer.MAX_VALUE));
}
