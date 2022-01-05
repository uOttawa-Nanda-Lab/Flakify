@Test public void acceptAndTransmitCookies() throws Exception {
  CookieManager cookieManager=new CookieManager();
  client.setCookieHandler(cookieManager);
  server.enqueue(new MockResponse().addHeader("set-cookie: c=oreo; domain=" + server.getCookieDomain()).setBody("A"));
  server.enqueue(new MockResponse().setBody("B"));
  server.play();
  URL url=server.getUrl("/");
  assertContent("A",client.open(url),Integer.MAX_VALUE);
  Map<String,List<String>> requestHeaders=Collections.emptyMap();
  assertEquals(Collections.singletonMap("Cookie",Arrays.asList("c=oreo")),cookieManager.get(url.toURI(),requestHeaders));
  assertContent("B",client.open(url),Integer.MAX_VALUE);
  RecordedRequest requestA=server.takeRequest();
  assertContainsNoneMatching(requestA.getHeaders(),"Cookie.*");
  RecordedRequest requestB=server.takeRequest();
  assertContains(requestB.getHeaders(),"cookie: c=oreo");
}
