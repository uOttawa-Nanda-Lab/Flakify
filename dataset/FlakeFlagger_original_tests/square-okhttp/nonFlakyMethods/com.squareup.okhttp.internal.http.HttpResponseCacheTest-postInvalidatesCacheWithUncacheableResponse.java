@Test public void postInvalidatesCacheWithUncacheableResponse() throws Exception {
  server.enqueue(new MockResponse().setBody("A").addHeader("Expires: " + formatDate(1,TimeUnit.HOURS)));
  server.enqueue(new MockResponse().setBody("B").setResponseCode(500));
  server.enqueue(new MockResponse().setBody("C"));
  server.play();
  URL url=server.getUrl("/");
  assertEquals("A",readAscii(openConnection(url)));
  HttpURLConnection invalidate=openConnection(url);
  invalidate.setRequestMethod("POST");
  addRequestBodyIfNecessary("POST",invalidate);
  assertEquals("B",readAscii(invalidate));
  assertEquals("C",readAscii(openConnection(url)));
}
