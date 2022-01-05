@Test public void testOverrideDropFirst() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"liquibase.drop-first:true");
  this.context.register(EmbeddedDataSourceConfiguration.class,LiquibaseAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  SpringLiquibase liquibase=this.context.getBean(SpringLiquibase.class);
  assertTrue(liquibase.isDropFirst());
}
