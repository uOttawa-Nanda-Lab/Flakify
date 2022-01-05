/** 
 * Equivalent to  {@link HttpResponseCacheTest#postInvalidatesCacheWithUncacheableResponse()} butdemonstrating that  {@link ResponseCache} provides no mechanism for cache invalidation as theresult of locally-made requests. In reality invalidation could take place from other clients at any time.
 */
@Test public void postInvalidatesCacheWithUncacheableResponse() throws Exception {
  server.enqueue(new MockResponse().setBody("A").addHeader("Expires: " + formatDate(1,TimeUnit.HOURS)));
  server.enqueue(new MockResponse().setBody("B").setResponseCode(500));
  server.play();
  URL url=server.getUrl("/");
  assertEquals("A",readAscii(openConnection(url)));
  HttpURLConnection invalidate=openConnection(url);
  invalidate.setRequestMethod("POST");
  addRequestBodyIfNecessary("POST",invalidate);
  assertEquals("B",readAscii(invalidate));
  assertEquals("A",readAscii(openConnection(url)));
}
