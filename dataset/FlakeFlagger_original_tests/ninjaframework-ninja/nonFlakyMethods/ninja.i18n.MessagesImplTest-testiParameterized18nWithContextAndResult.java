@Test public void testiParameterized18nWithContextAndResult(){
  when(ninjaProperties.getStringArray(NinjaConstant.applicationLanguages)).thenReturn(new String[]{"en","de","fr-FR"});
  Lang lang=new LangImpl(ninjaProperties);
  Messages messages=new MessagesImpl(ninjaProperties,lang);
  result=Results.ok();
  when(context.getAcceptLanguage()).thenReturn("en-US");
  assertEquals("this is the placeholder: test_parameter",messages.get("message_with_placeholder",context,Optional.of(result),"test_parameter").get());
  when(context.getAcceptLanguage()).thenReturn("en-CA");
  assertEquals("this is the placeholder: test_parameter",messages.get("message_with_placeholder",context,Optional.of(result),"test_parameter").get());
  when(context.getAcceptLanguage()).thenReturn("en-UK");
  assertEquals("this is the placeholder: test_parameter",messages.get("message_with_placeholder",context,Optional.of(result),"test_parameter").get());
  lang.setLanguage("de",result);
  assertEquals("das ist der platzhalter: test_parameter",messages.get("message_with_placeholder",context,Optional.of(result),"test_parameter").get());
  lang.setLanguage("de-DE",result);
  assertEquals("das ist der platzhalter: test_parameter",messages.get("message_with_placeholder",context,Optional.of(result),"test_parameter").get());
  result=Results.ok();
  when(context.getCookie(Mockito.anyString())).thenReturn(Cookie.builder("name","fr-FR").build());
  assertEquals("cest le placeholder: test_parameter",messages.get("message_with_placeholder",context,Optional.of(result),"test_parameter").get());
  result=Results.ok();
  lang.setLanguage("de-DE",result);
  assertEquals("das ist der platzhalter: test_parameter",messages.get("message_with_placeholder",context,Optional.of(result),"test_parameter").get());
}
