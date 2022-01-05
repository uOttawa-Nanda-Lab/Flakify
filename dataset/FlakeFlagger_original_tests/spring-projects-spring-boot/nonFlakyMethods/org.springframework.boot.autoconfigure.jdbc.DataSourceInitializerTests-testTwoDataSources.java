@Test public void testTwoDataSources() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"datasource.one.url=jdbc:hsqldb:mem:/one","datasource.one.driverClassName=org.hsqldb.Driver","datasource.two.url=jdbc:hsqldb:mem:/two","datasource.two.driverClassName=org.hsqldb.Driver");
  this.context.register(TwoDataSources.class,DataSourceInitializer.class,PropertyPlaceholderAutoConfiguration.class,DataSourceProperties.class);
  this.context.refresh();
  assertEquals(2,this.context.getBeanNamesForType(DataSource.class).length);
}
