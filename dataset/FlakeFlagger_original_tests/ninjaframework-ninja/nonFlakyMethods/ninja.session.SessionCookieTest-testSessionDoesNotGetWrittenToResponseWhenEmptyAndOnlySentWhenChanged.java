@Test public void testSessionDoesNotGetWrittenToResponseWhenEmptyAndOnlySentWhenChanged(){
  SessionCookie sessionCookie=new SessionCookieImpl(crypto,ninjaProperties);
  sessionCookie.init(context);
  sessionCookie.save(context,result);
  verify(result,never()).addCookie(Matchers.any(Cookie.class));
}
