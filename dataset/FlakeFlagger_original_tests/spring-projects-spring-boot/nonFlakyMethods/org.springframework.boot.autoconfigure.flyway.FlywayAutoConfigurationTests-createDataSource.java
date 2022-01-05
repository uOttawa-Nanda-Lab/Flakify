@Test public void createDataSource() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"flyway.url:jdbc:hsqldb:mem:flywaytest","flyway.user:sa");
  registerAndRefresh(EmbeddedDataSourceConfiguration.class,FlywayAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  Flyway flyway=this.context.getBean(Flyway.class);
  assertNotNull(flyway.getDataSource());
}
