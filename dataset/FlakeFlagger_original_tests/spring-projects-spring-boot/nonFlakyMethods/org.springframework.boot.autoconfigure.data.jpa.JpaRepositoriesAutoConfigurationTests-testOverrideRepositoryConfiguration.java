@Test public void testOverrideRepositoryConfiguration() throws Exception {
  prepareApplicationContext(CustomConfiguration.class);
  assertNotNull(this.context.getBean(org.springframework.boot.autoconfigure.data.alt.jpa.CityJpaRepository.class));
  assertNotNull(this.context.getBean(PlatformTransactionManager.class));
  assertNotNull(this.context.getBean(EntityManagerFactory.class));
}
