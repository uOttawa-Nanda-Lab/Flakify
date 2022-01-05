@Test public void testI18nAcceptLanguageHttpHeaderWithQualityScores(){
  when(ninjaProperties.getStringArray(NinjaConstant.applicationLanguages)).thenReturn(new String[]{"en","de","fr-FR"});
  Messages lang=new MessagesImpl(ninjaProperties,null);
  assertEquals("francaise",lang.get("language",Optional.of("da,fr-FR;q=0.8")).get());
  assertEquals("francaise",lang.get("language",Optional.of("da;q=0.9, fr-FR; q=0.8")).get());
  assertEquals("deutsch",lang.get("language",Optional.of("de,fr-FR;q=0.8")).get());
  assertEquals("deutsch",lang.get("language",Optional.of("de;q=0.9, fr-FR; q=0.8")).get());
}
