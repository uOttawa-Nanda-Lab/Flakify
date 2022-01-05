@Test public void changeLogDoesNotExist() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"flyway.locations:file:no-such-dir");
  this.thrown.expect(BeanCreationException.class);
  registerAndRefresh(EmbeddedDataSourceConfiguration.class,FlywayAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
}
