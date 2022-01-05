@Test public void testOverrideChangeLog() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"liquibase.change-log:classpath:/db/changelog/db.changelog-override.xml");
  this.context.register(EmbeddedDataSourceConfiguration.class,LiquibaseAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  SpringLiquibase liquibase=this.context.getBean(SpringLiquibase.class);
  assertEquals("classpath:/db/changelog/db.changelog-override.xml",liquibase.getChangeLog());
}
