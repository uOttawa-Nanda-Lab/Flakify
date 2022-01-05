@Test public void testThatFlashCookieClearOfOutgoingWorks(){
  Cookie cookie=Cookie.builder("NINJA_FLASH","hello=flashScope").build();
  when(context.getCookie("NINJA_FLASH")).thenReturn(cookie);
  FlashCookie flashCookie=new FlashCookieImpl(ninjaProperties);
  flashCookie.init(context);
  assertEquals("flashScope",flashCookie.get("hello"));
  flashCookie.put("funny new flash message","is there...");
  flashCookie.discard();
  assertEquals(2,((FlashCookieImpl)flashCookie).getCurrentFlashCookieData().size());
  assertEquals(0,((FlashCookieImpl)flashCookie).getOutgoingFlashCookieData().size());
}
