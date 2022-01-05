@Test public void testDataSourceExists() throws Exception {
  this.context.register(CommonsDataSourceConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(DataSource.class));
  this.context.close();
}
