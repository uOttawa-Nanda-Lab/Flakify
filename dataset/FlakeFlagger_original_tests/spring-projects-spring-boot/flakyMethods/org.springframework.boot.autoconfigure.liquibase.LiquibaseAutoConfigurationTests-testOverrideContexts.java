@Test public void testOverrideContexts() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"liquibase.contexts:test, production");
  this.context.register(EmbeddedDataSourceConfiguration.class,LiquibaseAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  SpringLiquibase liquibase=this.context.getBean(SpringLiquibase.class);
  assertEquals("test, production",liquibase.getContexts());
}
