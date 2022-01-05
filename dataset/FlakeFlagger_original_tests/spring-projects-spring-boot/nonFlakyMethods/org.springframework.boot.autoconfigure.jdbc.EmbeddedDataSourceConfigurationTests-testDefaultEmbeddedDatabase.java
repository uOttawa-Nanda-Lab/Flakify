@Test public void testDefaultEmbeddedDatabase() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(EmbeddedDataSourceConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(DataSource.class));
  this.context.close();
}
