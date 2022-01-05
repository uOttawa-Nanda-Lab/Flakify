@Test public void readAfterLastByte() throws Exception {
  server.enqueue(new MockResponse().setBody("ABC"));
  server.play();
  connection=client.open(server.getUrl("/"));
  InputStream in=connection.getInputStream();
  assertEquals("ABC",readAscii(in,3));
  assertEquals(-1,in.read());
  assertEquals(-1,in.read());
}
