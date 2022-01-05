@Test public void testGetLanguage(){
  Cookie cookie=Cookie.builder("NINJA_TEST" + NinjaConstant.LANG_COOKIE_SUFFIX,"de").build();
  when(ninjaProperties.getOrDie(NinjaConstant.applicationCookiePrefix)).thenReturn("NINJA_TEST");
  when(context.getCookie("NINJA_TEST" + NinjaConstant.LANG_COOKIE_SUFFIX)).thenReturn(cookie);
  Lang lang=new LangImpl(ninjaProperties);
  Result result=Results.ok();
  Optional<String> language=lang.getLanguage(context,Optional.of(result));
  assertEquals("de",language.get());
  result=Results.ok();
  cookie=Cookie.builder("NINJA_TEST" + NinjaConstant.LANG_COOKIE_SUFFIX,"en").build();
  result.addCookie(cookie);
  language=lang.getLanguage(context,Optional.of(result));
  assertEquals("en",language.get());
}
