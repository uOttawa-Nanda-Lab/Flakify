@Test public void testNoBatchConfiguration() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(EmptyConfiguration.class,BatchAutoConfiguration.class,EmbeddedDataSourceConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertEquals(0,this.context.getBeanNamesForType(JobLauncher.class).length);
  assertEquals(0,this.context.getBeanNamesForType(JobRepository.class).length);
}
