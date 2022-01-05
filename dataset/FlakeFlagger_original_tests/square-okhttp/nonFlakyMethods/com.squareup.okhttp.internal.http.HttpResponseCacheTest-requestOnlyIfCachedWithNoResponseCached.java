@Test public void requestOnlyIfCachedWithNoResponseCached() throws IOException {
  server.play();
  HttpURLConnection connection=openConnection(server.getUrl("/"));
  connection.addRequestProperty("Cache-Control","only-if-cached");
  assertGatewayTimeout(connection);
  assertEquals(1,cache.getRequestCount());
  assertEquals(0,cache.getNetworkCount());
  assertEquals(0,cache.getHitCount());
}
