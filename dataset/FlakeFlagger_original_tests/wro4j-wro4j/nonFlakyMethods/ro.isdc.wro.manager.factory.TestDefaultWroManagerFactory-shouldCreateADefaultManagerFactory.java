@Test public void shouldCreateADefaultManagerFactory(){
  assertEquals(BaseWroManagerFactory.class,victim.getFactory().getClass());
}
