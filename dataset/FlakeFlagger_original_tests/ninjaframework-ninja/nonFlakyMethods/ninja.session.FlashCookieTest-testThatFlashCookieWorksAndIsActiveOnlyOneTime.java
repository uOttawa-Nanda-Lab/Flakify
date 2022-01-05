@Test public void testThatFlashCookieWorksAndIsActiveOnlyOneTime(){
  Cookie cookie=Cookie.builder("NINJA_FLASH","hello=flashScope").build();
  when(context.getCookie("NINJA_FLASH")).thenReturn(cookie);
  FlashCookie flashCookie=new FlashCookieImpl(ninjaProperties);
  flashCookie.init(context);
  assertEquals("flashScope",flashCookie.get("hello"));
  flashCookie.put("another message","is there...");
  flashCookie.put("yet another message","is there...");
  flashCookie.save(context,result);
  verify(result).addCookie(cookieCaptor.capture());
  assertEquals("NINJA_FLASH",cookieCaptor.getValue().getName());
  assertEquals("another+message=is+there...&yet+another+message=is+there...",cookieCaptor.getValue().getValue());
  assertEquals(3,((FlashCookieImpl)flashCookie).getCurrentFlashCookieData().size());
  assertEquals(2,((FlashCookieImpl)flashCookie).getOutgoingFlashCookieData().size());
}
