@Test public void checkLocationsAllExist() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"flyway.locations:classpath:db/changelog,classpath:db/migration","flyway.check-location:true");
  registerAndRefresh(EmbeddedDataSourceConfiguration.class,FlywayAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
}
