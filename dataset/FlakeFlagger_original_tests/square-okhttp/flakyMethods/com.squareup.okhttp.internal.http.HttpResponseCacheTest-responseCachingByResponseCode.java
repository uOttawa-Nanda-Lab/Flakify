/** 
 * Test that response caching is consistent with the RI and the spec. http://www.w3.org/Protocols/rfc2616/rfc2616-sec13.html#sec13.4
 */
@Test public void responseCachingByResponseCode() throws Exception {
  assertCached(false,101);
  assertCached(false,102);
  assertCached(true,200);
  assertCached(false,201);
  assertCached(false,202);
  assertCached(true,203);
  assertCached(false,204);
  assertCached(false,205);
  assertCached(false,206);
  assertCached(false,207);
  assertCached(true,300);
  assertCached(true,301);
  for (int i=302; i <= 308; ++i) {
    assertCached(false,i);
  }
  for (int i=400; i <= 406; ++i) {
    assertCached(false,i);
  }
  assertCached(false,408);
  assertCached(false,409);
  for (int i=411; i <= 418; ++i) {
    assertCached(false,i);
  }
  for (int i=500; i <= 506; ++i) {
    assertCached(false,i);
  }
}
