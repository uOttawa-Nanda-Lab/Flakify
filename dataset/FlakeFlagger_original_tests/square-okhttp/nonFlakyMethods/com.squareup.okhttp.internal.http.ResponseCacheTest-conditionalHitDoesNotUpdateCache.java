/** 
 * Equivalent to  {@link HttpResponseCacheTest#conditionalHitUpdatesCache()}, except a Java standard cache has no means to update the headers for an existing entry so the behavior is different.
 */
@Test public void conditionalHitDoesNotUpdateCache() throws Exception {
  server.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(0,TimeUnit.SECONDS)).addHeader("Cache-Control: max-age=0").setBody("A"));
  server.enqueue(new MockResponse().addHeader("Cache-Control: max-age=30").addHeader("Allow: GET, HEAD").setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED));
  server.enqueue(new MockResponse().setBody("B").addHeader("Cache-Control: max-age=30"));
  server.enqueue(new MockResponse().setBody("C"));
  server.play();
  HttpURLConnection connection1=openConnection(server.getUrl("/a"));
  assertEquals("A",readAscii(connection1));
  assertEquals(null,connection1.getHeaderField("Allow"));
  HttpURLConnection connection2=openConnection(server.getUrl("/a"));
  assertEquals(HttpURLConnection.HTTP_OK,connection2.getResponseCode());
  assertEquals("A",readAscii(connection2));
  assertEquals("GET, HEAD",connection2.getHeaderField("Allow"));
  HttpURLConnection connection3=openConnection(server.getUrl("/a"));
  assertEquals("B",readAscii(connection3));
  HttpURLConnection connection4=openConnection(server.getUrl("/a"));
  assertEquals("B",readAscii(connection4));
  assertEquals(3,server.getRequestCount());
}
