@Test public void testFlashCookieSettingWorks(){
  FlashCookie flashCookie=new FlashCookieImpl(ninjaProperties);
  flashCookie.init(context);
  flashCookie.put("hello","flashScope");
  flashCookie.save(context,result);
  verify(result).addCookie(cookieCaptor.capture());
  assertEquals("NINJA_FLASH",cookieCaptor.getValue().getName());
  assertEquals("hello=flashScope",cookieCaptor.getValue().getValue());
  assertEquals(-1,cookieCaptor.getValue().getMaxAge());
  assertEquals(1,((FlashCookieImpl)flashCookie).getCurrentFlashCookieData().size());
  assertEquals(1,((FlashCookieImpl)flashCookie).getOutgoingFlashCookieData().size());
}
