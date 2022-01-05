@Test public void testOverrideDefaultSchema() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"liquibase.default-schema:public");
  this.context.register(EmbeddedDataSourceConfiguration.class,LiquibaseAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  SpringLiquibase liquibase=this.context.getBean(SpringLiquibase.class);
  assertEquals("public",liquibase.getDefaultSchema());
}
