@Test public void testNoHttpOnlyWorks() throws Exception {
  when(ninjaProperties.getBooleanWithDefault(NinjaConstant.sessionHttpOnly,true)).thenReturn(false);
  SessionCookie sessionCookie=new SessionCookieImpl(crypto,ninjaProperties);
  sessionCookie.init(context);
  sessionCookie.put("hello","session!");
  sessionCookie.save(context,result);
  verify(result).addCookie(cookieCaptor.capture());
  assertEquals(false,cookieCaptor.getValue().isHttpOnly());
}
