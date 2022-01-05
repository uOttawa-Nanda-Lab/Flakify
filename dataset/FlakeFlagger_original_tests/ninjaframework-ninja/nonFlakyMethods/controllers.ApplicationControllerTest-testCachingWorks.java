@Test public void testCachingWorks(){
  String response=ninjaTestBrowser.makeRequest(getServerAddress() + "/test_caching");
  assertTrue(response.contains("No cache key set."));
  response=ninjaTestBrowser.makeRequest(getServerAddress() + "/test_caching");
  assertTrue(response.contains("Cache key is: cacheKeyValue"));
}
