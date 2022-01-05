@Test public void singleByteReadIsSigned() throws IOException {
  server.enqueue(new MockResponse().setBody(new byte[]{-2,-1}));
  server.play();
  connection=client.open(server.getUrl("/"));
  InputStream in=connection.getInputStream();
  assertEquals(254,in.read());
  assertEquals(255,in.read());
  assertEquals(-1,in.read());
}
