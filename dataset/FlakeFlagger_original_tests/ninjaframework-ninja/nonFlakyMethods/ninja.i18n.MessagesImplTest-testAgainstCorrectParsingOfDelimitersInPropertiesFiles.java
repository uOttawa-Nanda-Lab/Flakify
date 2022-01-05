@Test public void testAgainstCorrectParsingOfDelimitersInPropertiesFiles(){
  when(ninjaProperties.getStringArray(NinjaConstant.applicationLanguages)).thenReturn(new String[]{"en","de","fr-FR"});
  Messages lang=new MessagesImpl(ninjaProperties,null);
  assertEquals("prop1, prop2, prop3",lang.get("a_propert_with_commas",Optional.of("en-US")).get());
}
