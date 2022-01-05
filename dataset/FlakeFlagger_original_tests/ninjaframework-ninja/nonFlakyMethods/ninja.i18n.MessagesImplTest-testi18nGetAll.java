@Test public void testi18nGetAll(){
  when(ninjaProperties.getStringArray(NinjaConstant.applicationLanguages)).thenReturn(new String[]{"en","de","fr-FR"});
  Messages lang=new MessagesImpl(ninjaProperties,null);
  Map<Object,Object> map=lang.getAll(Optional.of("en-US"));
  assertEquals(4,map.keySet().size());
  assertTrue(map.containsKey("language"));
  assertTrue(map.containsKey("message_with_placeholder"));
  assertTrue(map.containsKey("a_property_only_in_the_defaultLanguage"));
  assertTrue(map.containsKey("a_propert_with_commas"));
  assertEquals("english",map.get("language"));
  map=lang.getAll(Optional.of("de"));
  assertEquals(4,map.keySet().size());
  assertTrue(map.containsKey("language"));
  assertTrue(map.containsKey("message_with_placeholder"));
  assertTrue(map.containsKey("a_property_only_in_the_defaultLanguage"));
  assertTrue(map.containsKey("a_propert_with_commas"));
  assertEquals("deutsch",map.get("language"));
  assertEquals("das ist der platzhalter: {0}",map.get("message_with_placeholder"));
}
