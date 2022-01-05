@Test public void testiParameterized18n(){
  when(ninjaProperties.getStringArray(NinjaConstant.applicationLanguages)).thenReturn(new String[]{"en","de","fr-FR"});
  Messages lang=new MessagesImpl(ninjaProperties,null);
  assertEquals("this is the placeholder: test_parameter",lang.get("message_with_placeholder",Optional.of("en-US"),"test_parameter").get());
  assertEquals("this is the placeholder: test_parameter",lang.get("message_with_placeholder",Optional.of("en-CA"),"test_parameter").get());
  assertEquals("this is the placeholder: test_parameter",lang.get("message_with_placeholder",Optional.of("en-UK"),"test_parameter").get());
  assertEquals("das ist der platzhalter: test_parameter",lang.get("message_with_placeholder",Optional.of("de"),"test_parameter").get());
  assertEquals("das ist der platzhalter: test_parameter",lang.get("message_with_placeholder",Optional.of("de-DE"),"test_parameter").get());
}
