@Test public void cachePlusCookies() throws Exception {
  server.enqueue(new MockResponse().addHeader("Set-Cookie: a=FIRST; domain=" + server.getCookieDomain() + ";").addHeader("Last-Modified: " + formatDate(-1,TimeUnit.HOURS)).addHeader("Cache-Control: max-age=0").setBody("A"));
  server.enqueue(new MockResponse().addHeader("Set-Cookie: a=SECOND; domain=" + server.getCookieDomain() + ";").setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED));
  server.play();
  URL url=server.getUrl("/");
  assertEquals("A",readAscii(openConnection(url)));
  assertCookies(url,"a=FIRST");
  assertEquals("A",readAscii(openConnection(url)));
  assertCookies(url,"a=SECOND");
}
