@Test public void assertGetJobExecutorTwice(){
  when(jobFacade.loadJobRootConfiguration(true)).thenReturn(new TestDataflowJobConfiguration(false));
  AbstractElasticJobExecutor executor=JobExecutorFactory.getJobExecutor(new TestSimpleJob(null),jobFacade);
  AbstractElasticJobExecutor anotherExecutor=JobExecutorFactory.getJobExecutor(new TestSimpleJob(null),jobFacade);
  assertTrue(executor.hashCode() != anotherExecutor.hashCode());
}
