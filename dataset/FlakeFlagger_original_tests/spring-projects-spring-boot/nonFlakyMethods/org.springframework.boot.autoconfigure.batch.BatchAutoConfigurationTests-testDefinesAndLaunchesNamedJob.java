@Test public void testDefinesAndLaunchesNamedJob() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.batch.job.names:discreteRegisteredJob");
  this.context.register(NamedJobConfigurationWithRegisteredJob.class,EmbeddedDataSourceConfiguration.class,BatchAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  JobRepository repository=this.context.getBean(JobRepository.class);
  assertNotNull(this.context.getBean(JobLauncher.class));
  this.context.getBean(JobLauncherCommandLineRunner.class).run();
  assertNotNull(repository.getLastJobExecution("discreteRegisteredJob",new JobParameters()));
}
