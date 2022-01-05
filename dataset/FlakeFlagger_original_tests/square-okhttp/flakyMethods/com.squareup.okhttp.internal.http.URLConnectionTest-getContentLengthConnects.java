@Test public void getContentLengthConnects() throws Exception {
  server.enqueue(new MockResponse().setBody("ABC"));
  server.play();
  connection=client.open(server.getUrl("/"));
  assertEquals(3,connection.getContentLength());
}
