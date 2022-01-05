@Test public void connectionPooling() throws Exception {
  server.enqueue(new MockResponse().setBody("abc"));
  server.enqueue(new MockResponse().setBody("def"));
  server.enqueue(new MockResponse().setBody("ghi"));
  server.play();
  onSuccess(new Request.Builder().url(server.getUrl("/a")).build()).assertBody("abc");
  onSuccess(new Request.Builder().url(server.getUrl("/b")).build()).assertBody("def");
  onSuccess(new Request.Builder().url(server.getUrl("/c")).build()).assertBody("ghi");
  assertEquals(0,server.takeRequest().getSequenceNumber());
  assertEquals(1,server.takeRequest().getSequenceNumber());
  assertEquals(2,server.takeRequest().getSequenceNumber());
}
