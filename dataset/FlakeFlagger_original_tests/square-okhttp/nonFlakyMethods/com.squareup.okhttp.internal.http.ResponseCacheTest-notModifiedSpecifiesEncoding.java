@Test public void notModifiedSpecifiesEncoding() throws Exception {
  server.enqueue(new MockResponse().setBody(gzip("ABCABCABC".getBytes("UTF-8"))).addHeader("Content-Encoding: gzip").addHeader("Last-Modified: " + formatDate(-2,TimeUnit.HOURS)).addHeader("Expires: " + formatDate(-1,TimeUnit.HOURS)));
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED).addHeader("Content-Encoding: gzip"));
  server.enqueue(new MockResponse().setBody("DEFDEFDEF"));
  server.play();
  assertEquals("ABCABCABC",readAscii(openConnection(server.getUrl("/"))));
  assertEquals("ABCABCABC",readAscii(openConnection(server.getUrl("/"))));
  assertEquals("DEFDEFDEF",readAscii(openConnection(server.getUrl("/"))));
}
