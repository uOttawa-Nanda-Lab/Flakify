@Test public void testNoDataSource() throws Exception {
  this.context.register(LiquibaseAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertEquals(0,this.context.getBeanNamesForType(SpringLiquibase.class).length);
}
