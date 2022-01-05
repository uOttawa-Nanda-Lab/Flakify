@Test public void flywayDataSource() throws Exception {
  registerAndRefresh(FlywayDataSourceConfiguration.class,EmbeddedDataSourceConfiguration.class,FlywayAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  Flyway flyway=this.context.getBean(Flyway.class);
  assertNotNull(flyway.getDataSource());
}
