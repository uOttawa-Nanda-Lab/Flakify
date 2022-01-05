@Test public void testSessionCookieSettingWorks() throws Exception {
  SessionCookie sessionCookie=new SessionCookieImpl(crypto,ninjaProperties);
  sessionCookie.init(context);
  sessionCookie.put("hello","session!");
  sessionCookie.save(context,result);
  verify(result).addCookie(cookieCaptor.capture());
  assertEquals("NINJA_SESSION",cookieCaptor.getValue().getName());
  String cookieString=cookieCaptor.getValue().getValue();
  String cookieFromSign=cookieString.substring(cookieString.indexOf("-") + 1);
  String computedSign=crypto.signHmacSha1(cookieFromSign);
  assertEquals(computedSign,cookieString.substring(0,cookieString.indexOf("-")));
  assertTrue(cookieString.contains("___TS"));
}
