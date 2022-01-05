@Test public void testNoHttpsOnlyWorks() throws Exception {
  when(ninjaProperties.getBooleanWithDefault(NinjaConstant.sessionTransferredOverHttpsOnly,true)).thenReturn(false);
  SessionCookie sessionCookie=new SessionCookieImpl(crypto,ninjaProperties);
  sessionCookie.init(context);
  sessionCookie.put("hello","session!");
  sessionCookie.save(context,result);
  verify(result).addCookie(cookieCaptor.capture());
  assertEquals(false,cookieCaptor.getValue().isSecure());
}
