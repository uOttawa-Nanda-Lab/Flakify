@Test public void bug2939() throws Exception {
  MockResponse response=new MockResponse().setChunkedBody("ABCDE\nFGHIJ\nKLMNO\nPQR",8);
  server.enqueue(response);
  server.enqueue(response);
  server.play();
  assertContent("ABCDE",client.open(server.getUrl("/")),5);
  assertContent("ABCDE",client.open(server.getUrl("/")),5);
}
