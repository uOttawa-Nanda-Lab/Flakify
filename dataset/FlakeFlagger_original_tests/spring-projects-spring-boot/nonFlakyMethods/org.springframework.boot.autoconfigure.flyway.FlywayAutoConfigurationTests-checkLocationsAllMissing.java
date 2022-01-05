@Test public void checkLocationsAllMissing() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"flyway.locations:classpath:db/missing1,classpath:db/migration2","flyway.check-location:true");
  this.thrown.expect(BeanCreationException.class);
  this.thrown.expectMessage("Cannot find migrations location in");
  registerAndRefresh(EmbeddedDataSourceConfiguration.class,FlywayAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
}
