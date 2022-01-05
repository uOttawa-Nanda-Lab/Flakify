@Test public void testDefaultRepositoryConfiguration() throws Exception {
  prepareApplicationContext(TestConfiguration.class);
  assertNotNull(this.context.getBean(CityRepository.class));
  assertNotNull(this.context.getBean(PlatformTransactionManager.class));
  assertNotNull(this.context.getBean(EntityManagerFactory.class));
}
