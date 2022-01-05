/** 
 * We had a bug where we weren't closing Gzip streams on redirects. https://github.com/square/okhttp/issues/441
 */
@Test public void gzipWithRedirectAndConnectionReuse() throws Exception {
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_MOVED_TEMP).addHeader("Location: /foo").addHeader("Content-Encoding: gzip").setBody(gzip("Moved! Moved! Moved!".getBytes(UTF_8))));
  server.enqueue(new MockResponse().setBody("This is the new page!"));
  server.play();
  HttpURLConnection connection=client.open(server.getUrl("/"));
  assertContent("This is the new page!",connection);
  RecordedRequest requestA=server.takeRequest();
  assertEquals(0,requestA.getSequenceNumber());
  RecordedRequest requestB=server.takeRequest();
  assertEquals(1,requestB.getSequenceNumber());
}
