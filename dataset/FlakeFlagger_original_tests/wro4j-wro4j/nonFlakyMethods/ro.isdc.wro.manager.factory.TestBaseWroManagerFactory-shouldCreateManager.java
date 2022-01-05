@Test public void shouldCreateManager() throws Exception {
  final WroManager manager=victim.create();
  Assert.assertNotNull(manager);
  Assert.assertEquals(NoOpNamingStrategy.class,manager.getNamingStrategy().getClass());
}
