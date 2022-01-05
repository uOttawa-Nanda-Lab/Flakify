@Test public void connectionIsReturnedToPoolAfterConditionalSuccess() throws Exception {
  server.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(-1,TimeUnit.HOURS)).addHeader("Cache-Control: max-age=0").setBody("A"));
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED));
  server.enqueue(new MockResponse().setBody("B"));
  server.play();
  assertEquals("A",readAscii(openConnection(server.getUrl("/a"))));
  assertEquals("A",readAscii(openConnection(server.getUrl("/a"))));
  assertEquals("B",readAscii(openConnection(server.getUrl("/b"))));
  assertEquals(0,server.takeRequest().getSequenceNumber());
  assertEquals(1,server.takeRequest().getSequenceNumber());
  assertEquals(2,server.takeRequest().getSequenceNumber());
}
