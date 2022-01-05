@Test public void shouldUseCorrectDefaultStrategy(){
  assertEquals(XmlModelFactory.class,victim.getDefaultStrategy().getClass());
}
