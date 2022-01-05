@Test public void testNoDataSource() throws Exception {
  this.context.register(PropertyPlaceholderAutoConfiguration.class,getAutoConfigureClass());
  this.expected.expect(BeanCreationException.class);
  this.expected.expectMessage("No qualifying bean");
  this.expected.expectMessage("DataSource");
  this.context.refresh();
}
