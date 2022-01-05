@Test public void makeSureSessionsGetSentToClient(){
  Map<String,String> headers=Maps.newHashMap();
  HttpResponse httpResponse=ninjaTestBrowser.makeRequestAndGetResponse(getServerAddress() + "session",headers);
  assertEquals(1,ninjaTestBrowser.getCookies().size());
  Cookie cookie=ninjaTestBrowser.getCookieWithName("NINJA_SESSION");
  assertTrue(cookie != null);
  assertTrue(cookie.getValue().contains("___TS"));
  assertTrue(cookie.getValue().contains("username"));
  assertTrue(cookie.getValue().contains("kevin"));
}
