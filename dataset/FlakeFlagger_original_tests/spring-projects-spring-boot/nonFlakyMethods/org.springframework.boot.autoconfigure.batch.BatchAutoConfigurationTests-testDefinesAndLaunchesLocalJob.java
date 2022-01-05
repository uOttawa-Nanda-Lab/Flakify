@Test public void testDefinesAndLaunchesLocalJob() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.batch.job.names:discreteLocalJob");
  this.context.register(NamedJobConfigurationWithLocalJob.class,EmbeddedDataSourceConfiguration.class,BatchAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(JobLauncher.class));
  this.context.getBean(JobLauncherCommandLineRunner.class).run();
  assertNotNull(this.context.getBean(JobRepository.class).getLastJobExecution("discreteLocalJob",new JobParameters()));
}
