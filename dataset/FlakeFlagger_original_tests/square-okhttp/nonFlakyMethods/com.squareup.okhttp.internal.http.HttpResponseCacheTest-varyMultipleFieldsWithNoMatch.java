@Test public void varyMultipleFieldsWithNoMatch() throws Exception {
  server.enqueue(new MockResponse().addHeader("Cache-Control: max-age=60").addHeader("Vary: Accept-Language, Accept-Charset").addHeader("Vary: Accept-Encoding").setBody("A"));
  server.enqueue(new MockResponse().setBody("B"));
  server.play();
  URL url=server.getUrl("/");
  URLConnection frConnection=openConnection(url);
  frConnection.addRequestProperty("Accept-Language","fr-CA");
  frConnection.addRequestProperty("Accept-Charset","UTF-8");
  frConnection.addRequestProperty("Accept-Encoding","identity");
  assertEquals("A",readAscii(frConnection));
  URLConnection enConnection=openConnection(url);
  enConnection.addRequestProperty("Accept-Language","en-CA");
  enConnection.addRequestProperty("Accept-Charset","UTF-8");
  enConnection.addRequestProperty("Accept-Encoding","identity");
  assertEquals("B",readAscii(enConnection));
}
