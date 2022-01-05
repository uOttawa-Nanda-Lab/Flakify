@Test public void testDisableLaunchesJob() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.batch.job.enabled:false");
  this.context.register(JobConfiguration.class,EmbeddedDataSourceConfiguration.class,BatchAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(JobLauncher.class));
  assertEquals(0,this.context.getBeanNamesForType(CommandLineRunner.class).length);
}
