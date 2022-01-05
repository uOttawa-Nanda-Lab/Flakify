public void testDisconnectAtStart() throws Exception {
  server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START));
  server.enqueue(new MockResponse());
  server.enqueue(new MockResponse());
  server.play();
  try {
    server.getUrl("/a").openConnection().getInputStream();
  }
 catch (  IOException e) {
  }
  server.getUrl("/b").openConnection().getInputStream();
}
