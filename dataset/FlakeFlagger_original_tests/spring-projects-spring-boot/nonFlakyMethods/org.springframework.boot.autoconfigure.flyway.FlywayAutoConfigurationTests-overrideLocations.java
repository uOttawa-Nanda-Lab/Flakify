@Test public void overrideLocations() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"flyway.locations:classpath:db/changelog,classpath:db/migration");
  registerAndRefresh(EmbeddedDataSourceConfiguration.class,FlywayAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  Flyway flyway=this.context.getBean(Flyway.class);
  assertEquals("[classpath:db/changelog, classpath:db/migration]",Arrays.asList(flyway.getLocations()).toString());
}
