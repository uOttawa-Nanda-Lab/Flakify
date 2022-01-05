@Test public void testThatCorrectMethodOfNinjaPropertiesIsUsedSoThatStuffBreaksWhenPropertyIsAbsent(){
  FlashCookie flashCookie=new FlashCookieImpl(ninjaProperties);
  verify(ninjaProperties).getOrDie(NinjaConstant.applicationCookiePrefix);
}
