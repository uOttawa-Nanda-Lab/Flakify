@Test public void testDefaultSpringLiquibase() throws Exception {
  this.context.register(EmbeddedDataSourceConfiguration.class,LiquibaseAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  SpringLiquibase liquibase=this.context.getBean(SpringLiquibase.class);
  assertEquals("classpath:/db/changelog/db.changelog-master.yaml",liquibase.getChangeLog());
  assertNull(liquibase.getContexts());
  assertNull(liquibase.getDefaultSchema());
  assertFalse(liquibase.isDropFirst());
}
