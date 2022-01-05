@Test public void setIfModifiedSince() throws Exception {
  Date since=new Date();
  server.enqueue(new MockResponse().setBody("A"));
  server.play();
  URL url=server.getUrl("/");
  URLConnection connection=openConnection(url);
  connection.setIfModifiedSince(since.getTime());
  assertEquals("A",readAscii(connection));
  RecordedRequest request=server.takeRequest();
  assertTrue(request.getHeaders().contains("If-Modified-Since: " + formatDate(since)));
}
