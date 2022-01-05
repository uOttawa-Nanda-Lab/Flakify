@Test public void conditionalHitUpdatesCache() throws Exception {
  server.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(0,TimeUnit.SECONDS)).addHeader("Cache-Control: max-age=0").setBody("A"));
  server.enqueue(new MockResponse().addHeader("Cache-Control: max-age=30").addHeader("Allow: GET, HEAD").setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED));
  server.enqueue(new MockResponse().setBody("B"));
  server.play();
  HttpURLConnection connection1=openConnection(server.getUrl("/a"));
  assertEquals("A",readAscii(connection1));
  assertEquals(null,connection1.getHeaderField("Allow"));
  HttpURLConnection connection2=openConnection(server.getUrl("/a"));
  assertEquals(HttpURLConnection.HTTP_OK,connection2.getResponseCode());
  assertEquals("A",readAscii(connection2));
  assertEquals("GET, HEAD",connection2.getHeaderField("Allow"));
  HttpURLConnection connection3=openConnection(server.getUrl("/a"));
  assertEquals("A",readAscii(connection3));
  assertEquals("GET, HEAD",connection3.getHeaderField("Allow"));
  assertEquals(2,server.getRequestCount());
}
