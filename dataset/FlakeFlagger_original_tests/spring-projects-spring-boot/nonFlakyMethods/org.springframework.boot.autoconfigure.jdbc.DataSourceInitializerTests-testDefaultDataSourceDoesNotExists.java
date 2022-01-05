@Test public void testDefaultDataSourceDoesNotExists() throws Exception {
  this.context.register(DataSourceInitializer.class,PropertyPlaceholderAutoConfiguration.class,DataSourceProperties.class);
  this.context.refresh();
  assertEquals(0,this.context.getBeanNamesForType(DataSource.class).length);
}
