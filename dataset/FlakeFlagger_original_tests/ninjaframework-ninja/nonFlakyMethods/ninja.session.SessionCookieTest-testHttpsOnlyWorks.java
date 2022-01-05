@Test public void testHttpsOnlyWorks() throws Exception {
  SessionCookie sessionCookie=new SessionCookieImpl(crypto,ninjaProperties);
  sessionCookie.init(context);
  sessionCookie.put("hello","session!");
  sessionCookie.save(context,result);
  verify(result).addCookie(cookieCaptor.capture());
  assertEquals(true,cookieCaptor.getValue().isSecure());
}
