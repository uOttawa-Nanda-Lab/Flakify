@Test public void redirectToCachedResult() throws Exception {
  server.enqueue(new MockResponse().addHeader("Cache-Control: max-age=60").setBody("ABC"));
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_MOVED_PERM).addHeader("Location: /foo"));
  server.enqueue(new MockResponse().setBody("DEF"));
  server.play();
  assertEquals("ABC",readAscii(openConnection(server.getUrl("/foo"))));
  RecordedRequest request1=server.takeRequest();
  assertEquals("GET /foo HTTP/1.1",request1.getRequestLine());
  assertEquals(0,request1.getSequenceNumber());
  assertEquals("ABC",readAscii(openConnection(server.getUrl("/bar"))));
  RecordedRequest request2=server.takeRequest();
  assertEquals("GET /bar HTTP/1.1",request2.getRequestLine());
  assertEquals(1,request2.getSequenceNumber());
  assertEquals("DEF",readAscii(openConnection(server.getUrl("/baz"))));
  RecordedRequest request3=server.takeRequest();
  assertEquals("GET /baz HTTP/1.1",request3.getRequestLine());
  assertEquals(2,request3.getSequenceNumber());
}
