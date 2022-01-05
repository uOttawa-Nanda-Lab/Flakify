@Test public void responseSourceHeaderCached() throws IOException {
  server.enqueue(new MockResponse().setBody("A").addHeader("Cache-Control: max-age=30").addHeader("Date: " + formatDate(0,TimeUnit.MINUTES)));
  server.play();
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  URLConnection connection=openConnection(server.getUrl("/"));
  connection.addRequestProperty("Cache-Control","only-if-cached");
  assertEquals("A",readAscii(connection));
  String source=connection.getHeaderField(OkHeaders.RESPONSE_SOURCE);
  assertEquals(ResponseSource.CACHE + " 200",source);
}
