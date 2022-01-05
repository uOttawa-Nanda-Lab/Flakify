@Test public void setProtocols() throws Exception {
  server.enqueue(new MockResponse().setBody("A"));
  server.play();
  client.setProtocols(Arrays.asList(Protocol.HTTP_11));
  assertContent("A",client.open(server.getUrl("/")));
}
