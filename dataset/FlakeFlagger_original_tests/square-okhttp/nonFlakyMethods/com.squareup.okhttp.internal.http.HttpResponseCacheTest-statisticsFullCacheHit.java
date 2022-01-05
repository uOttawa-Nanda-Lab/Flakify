@Test public void statisticsFullCacheHit() throws Exception {
  server.enqueue(new MockResponse().addHeader("Cache-Control: max-age=60").setBody("A"));
  server.play();
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  assertEquals(1,cache.getRequestCount());
  assertEquals(1,cache.getNetworkCount());
  assertEquals(0,cache.getHitCount());
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  assertEquals(3,cache.getRequestCount());
  assertEquals(1,cache.getNetworkCount());
  assertEquals(2,cache.getHitCount());
}
