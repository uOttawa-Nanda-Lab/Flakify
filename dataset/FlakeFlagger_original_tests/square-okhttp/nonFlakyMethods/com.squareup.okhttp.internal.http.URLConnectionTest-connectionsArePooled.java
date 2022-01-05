@Test public void connectionsArePooled() throws Exception {
  MockResponse response=new MockResponse().setBody("ABCDEFGHIJKLMNOPQR");
  server.enqueue(response);
  server.enqueue(response);
  server.enqueue(response);
  server.play();
  assertContent("ABCDEFGHIJKLMNOPQR",client.open(server.getUrl("/foo")));
  assertEquals(0,server.takeRequest().getSequenceNumber());
  assertContent("ABCDEFGHIJKLMNOPQR",client.open(server.getUrl("/bar?baz=quux")));
  assertEquals(1,server.takeRequest().getSequenceNumber());
  assertContent("ABCDEFGHIJKLMNOPQR",client.open(server.getUrl("/z")));
  assertEquals(2,server.takeRequest().getSequenceNumber());
}
