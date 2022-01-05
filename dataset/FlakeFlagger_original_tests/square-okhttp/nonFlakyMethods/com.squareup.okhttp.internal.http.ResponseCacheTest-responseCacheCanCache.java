/** 
 * Tests that the ResponseCache can cache something. The InMemoryResponseCache only caches GET requests.
 */
@Test public void responseCacheCanCache() throws Exception {
  testRequestMethod("GET",true);
}
