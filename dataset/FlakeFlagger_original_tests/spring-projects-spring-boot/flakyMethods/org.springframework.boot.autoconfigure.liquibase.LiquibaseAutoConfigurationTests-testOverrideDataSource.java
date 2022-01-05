@Test public void testOverrideDataSource() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"liquibase.url:jdbc:hsqldb:mem:liquibase","liquibase.user:sa");
  this.context.register(EmbeddedDataSourceConfiguration.class,LiquibaseAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  SpringLiquibase liquibase=this.context.getBean(SpringLiquibase.class);
  assertEquals("jdbc:hsqldb:mem:liquibase",liquibase.getDataSource().getConnection().getMetaData().getURL());
}
