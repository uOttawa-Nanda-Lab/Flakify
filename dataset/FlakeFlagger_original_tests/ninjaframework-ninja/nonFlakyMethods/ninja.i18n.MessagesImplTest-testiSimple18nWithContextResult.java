@Test public void testiSimple18nWithContextResult(){
  when(ninjaProperties.getStringArray(NinjaConstant.applicationLanguages)).thenReturn(new String[]{"en","de","fr-FR"});
  Lang lang=new LangImpl(ninjaProperties);
  Messages messages=new MessagesImpl(ninjaProperties,lang);
  result=Results.ok();
  when(context.getAcceptLanguage()).thenReturn("en-US");
  assertEquals("english",messages.get("language",context,Optional.of(result)).get());
  when(context.getAcceptLanguage()).thenReturn("en-CA");
  assertEquals("english",messages.get("language",context,Optional.of(result)).get());
  when(context.getAcceptLanguage()).thenReturn("en-UK");
  assertEquals("english",messages.get("language",context,Optional.of(result)).get());
  lang.setLanguage("de",result);
  assertEquals("deutsch",messages.get("language",context,Optional.of(result)).get());
  result=Results.ok();
  lang.setLanguage("de-DE",result);
  assertEquals("deutsch",messages.get("language",context,Optional.of(result)).get());
  result=Results.ok();
  when(context.getCookie(Mockito.anyString())).thenReturn(Cookie.builder("name","fr-FR").build());
  assertEquals("francaise",messages.get("language",context,Optional.of(result)).get());
  result=Results.ok();
  lang.setLanguage("de-DE",result);
  assertEquals("deutsch",messages.get("language",context,Optional.of(result)).get());
}
