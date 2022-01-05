@Test public void responsesAreCached() throws IOException {
  client.setOkResponseCache(cache);
  server.enqueue(new MockResponse().addHeader("cache-control: max-age=60").setBody("A"));
  server.play();
  assertContent("A",client.open(server.getUrl("/")),Integer.MAX_VALUE);
  assertEquals(1,cache.getRequestCount());
  assertEquals(1,cache.getNetworkCount());
  assertEquals(0,cache.getHitCount());
  assertContent("A",client.open(server.getUrl("/")),Integer.MAX_VALUE);
  assertContent("A",client.open(server.getUrl("/")),Integer.MAX_VALUE);
  assertEquals(3,cache.getRequestCount());
  assertEquals(1,cache.getNetworkCount());
  assertEquals(2,cache.getHitCount());
}
