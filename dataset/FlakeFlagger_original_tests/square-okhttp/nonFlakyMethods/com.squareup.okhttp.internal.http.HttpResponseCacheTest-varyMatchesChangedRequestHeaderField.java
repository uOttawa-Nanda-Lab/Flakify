@Test public void varyMatchesChangedRequestHeaderField() throws Exception {
  server.enqueue(new MockResponse().addHeader("Cache-Control: max-age=60").addHeader("Vary: Accept-Language").setBody("A"));
  server.enqueue(new MockResponse().setBody("B"));
  server.play();
  URL url=server.getUrl("/");
  HttpURLConnection frConnection=openConnection(url);
  frConnection.addRequestProperty("Accept-Language","fr-CA");
  assertEquals("A",readAscii(frConnection));
  HttpURLConnection enConnection=openConnection(url);
  enConnection.addRequestProperty("Accept-Language","en-US");
  assertEquals("B",readAscii(enConnection));
}
