/** 
 * Confirm the ResponseCache can elect to not cache something. The InMemoryResponseCache only caches GET requests.
 */
@Test public void responseCacheCanIgnore() throws Exception {
  testRequestMethod("HEAD",false);
}
