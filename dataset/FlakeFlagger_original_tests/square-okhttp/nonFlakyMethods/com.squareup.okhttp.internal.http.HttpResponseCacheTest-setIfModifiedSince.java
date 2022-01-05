/** 
 * Confirm that  {@link URLConnection#setIfModifiedSince} causes anIf-Modified-Since header with a GMT timestamp. https://code.google.com/p/android/issues/detail?id=66135
 */
@Test public void setIfModifiedSince() throws Exception {
  server.enqueue(new MockResponse().setBody("A"));
  server.play();
  URL url=server.getUrl("/");
  URLConnection connection=openConnection(url);
  connection.setIfModifiedSince(1393666200000L);
  assertEquals("A",readAscii(connection));
  RecordedRequest request=server.takeRequest();
  String ifModifiedSinceHeader=request.getHeader("If-Modified-Since");
  assertEquals("Sat, 01 Mar 2014 09:30:00 GMT",ifModifiedSinceHeader);
}
