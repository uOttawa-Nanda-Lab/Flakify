@Test public void testDefaultDataSourceExists() throws Exception {
  this.context.register(DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(DataSource.class));
}
