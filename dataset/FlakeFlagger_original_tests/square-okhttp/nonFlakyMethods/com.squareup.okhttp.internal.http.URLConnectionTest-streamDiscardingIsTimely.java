@Test public void streamDiscardingIsTimely() throws Exception {
  server.enqueue(new MockResponse().setBody(new byte[10000]).throttleBody(100,10,MILLISECONDS));
  server.enqueue(new MockResponse().setBody("A"));
  server.play();
  long startNanos=System.nanoTime();
  URLConnection connection1=client.open(server.getUrl("/"));
  InputStream in=connection1.getInputStream();
  in.close();
  long elapsedNanos=System.nanoTime() - startNanos;
  long elapsedMillis=NANOSECONDS.toMillis(elapsedNanos);
  assertTrue(String.format("Time to close: %sms",elapsedMillis),elapsedMillis < 500);
  assertContent("A",client.open(server.getUrl("/")));
  assertEquals(0,server.takeRequest().getSequenceNumber());
  assertEquals(0,server.takeRequest().getSequenceNumber());
}
