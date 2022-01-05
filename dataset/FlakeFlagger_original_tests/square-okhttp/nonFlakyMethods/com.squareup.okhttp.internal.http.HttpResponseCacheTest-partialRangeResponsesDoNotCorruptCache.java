@Test public void partialRangeResponsesDoNotCorruptCache() throws Exception {
  server.enqueue(new MockResponse().setBody("AA").setResponseCode(HttpURLConnection.HTTP_PARTIAL).addHeader("Expires: " + formatDate(1,TimeUnit.HOURS)).addHeader("Content-Range: bytes 1000-1001/2000"));
  server.enqueue(new MockResponse().setBody("BB"));
  server.play();
  URL url=server.getUrl("/");
  URLConnection range=openConnection(url);
  range.addRequestProperty("Range","bytes=1000-1001");
  assertEquals("AA",readAscii(range));
  assertEquals("BB",readAscii(openConnection(url)));
}
