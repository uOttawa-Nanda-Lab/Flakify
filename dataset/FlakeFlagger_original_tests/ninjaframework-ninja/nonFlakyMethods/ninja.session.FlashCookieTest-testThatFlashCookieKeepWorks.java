@Test public void testThatFlashCookieKeepWorks(){
  Cookie cookie=Cookie.builder("NINJA_FLASH","hello=flashScope").build();
  when(context.getCookie("NINJA_FLASH")).thenReturn(cookie);
  FlashCookie flashCookie=new FlashCookieImpl(ninjaProperties);
  flashCookie.init(context);
  assertEquals("flashScope",flashCookie.get("hello"));
  assertEquals(1,((FlashCookieImpl)flashCookie).getCurrentFlashCookieData().size());
  assertEquals(0,((FlashCookieImpl)flashCookie).getOutgoingFlashCookieData().size());
  flashCookie.keep();
  assertEquals(1,((FlashCookieImpl)flashCookie).getCurrentFlashCookieData().size());
  assertEquals(1,((FlashCookieImpl)flashCookie).getOutgoingFlashCookieData().size());
}
