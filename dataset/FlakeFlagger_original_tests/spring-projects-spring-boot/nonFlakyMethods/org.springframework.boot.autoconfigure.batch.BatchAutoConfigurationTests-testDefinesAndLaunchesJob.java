@Test public void testDefinesAndLaunchesJob() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(JobConfiguration.class,EmbeddedDataSourceConfiguration.class,BatchAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(JobLauncher.class));
  this.context.getBean(JobLauncherCommandLineRunner.class).run();
  assertNotNull(this.context.getBean(JobRepository.class).getLastJobExecution("job",new JobParameters()));
}
