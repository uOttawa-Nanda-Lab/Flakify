@Test public void testDefaultDataSourceCanBeOverridden() throws Exception {
  this.context.register(TestDataSourceConfiguration.class,DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  DataSource dataSource=this.context.getBean(DataSource.class);
  assertTrue("DataSource is wrong type: " + dataSource,dataSource instanceof BasicDataSource);
}
