@Test public void varyMultipleFieldValuesWithNoMatch() throws Exception {
  server.enqueue(new MockResponse().addHeader("Cache-Control: max-age=60").addHeader("Vary: Accept-Language").setBody("A"));
  server.enqueue(new MockResponse().setBody("B"));
  server.play();
  URL url=server.getUrl("/");
  URLConnection connection1=openConnection(url);
  connection1.addRequestProperty("Accept-Language","fr-CA, fr-FR");
  connection1.addRequestProperty("Accept-Language","en-US");
  assertEquals("A",readAscii(connection1));
  URLConnection connection2=openConnection(url);
  connection2.addRequestProperty("Accept-Language","fr-CA");
  connection2.addRequestProperty("Accept-Language","en-US");
  assertEquals("B",readAscii(connection2));
}
