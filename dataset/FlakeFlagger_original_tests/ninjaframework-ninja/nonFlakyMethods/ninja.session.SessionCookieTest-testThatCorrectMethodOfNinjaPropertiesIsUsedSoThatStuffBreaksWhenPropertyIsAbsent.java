@Test public void testThatCorrectMethodOfNinjaPropertiesIsUsedSoThatStuffBreaksWhenPropertyIsAbsent(){
  when(ninjaProperties.getOrDie(NinjaConstant.applicationCookiePrefix)).thenReturn(null);
  SessionCookie sessionCookie=new SessionCookieImpl(crypto,ninjaProperties);
  verify(ninjaProperties).getOrDie(NinjaConstant.applicationCookiePrefix);
}
