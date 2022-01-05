@Test public void statisticsConditionalCacheMiss() throws Exception {
  server.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(-1,TimeUnit.HOURS)).addHeader("Cache-Control: max-age=0").setBody("A"));
  server.enqueue(new MockResponse().setBody("B"));
  server.enqueue(new MockResponse().setBody("C"));
  server.play();
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  assertEquals(1,cache.getRequestCount());
  assertEquals(1,cache.getNetworkCount());
  assertEquals(0,cache.getHitCount());
  assertEquals("B",readAscii(openConnection(server.getUrl("/"))));
  assertEquals("C",readAscii(openConnection(server.getUrl("/"))));
  assertEquals(3,cache.getRequestCount());
  assertEquals(3,cache.getNetworkCount());
  assertEquals(0,cache.getHitCount());
}
