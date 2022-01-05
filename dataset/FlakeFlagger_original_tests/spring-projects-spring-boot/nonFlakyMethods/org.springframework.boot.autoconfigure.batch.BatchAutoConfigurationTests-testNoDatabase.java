@Test public void testNoDatabase() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(TestCustomConfiguration.class,BatchAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(JobLauncher.class));
  JobExplorer explorer=this.context.getBean(JobExplorer.class);
  assertNotNull(explorer);
  assertEquals(0,explorer.getJobInstances("job",0,100).size());
}
