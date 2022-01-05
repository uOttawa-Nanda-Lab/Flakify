@Test public void testNamedParameterJdbcTemplateExists() throws Exception {
  this.context.register(DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(NamedParameterJdbcOperations.class));
}
