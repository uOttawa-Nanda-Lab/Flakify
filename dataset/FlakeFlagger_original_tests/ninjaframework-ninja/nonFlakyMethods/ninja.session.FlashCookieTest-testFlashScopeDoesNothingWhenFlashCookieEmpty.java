@Test public void testFlashScopeDoesNothingWhenFlashCookieEmpty(){
  FlashCookie flashCookie=new FlashCookieImpl(ninjaProperties);
  flashCookie.init(context);
  flashCookie.save(context,result);
  verify(result,never()).addCookie(Matchers.any(Cookie.class));
}
