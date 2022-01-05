@Test public void assertGetJobExecutorForSimpleJob(){
  when(jobFacade.loadJobRootConfiguration(true)).thenReturn(new TestSimpleJobConfiguration());
  assertThat(JobExecutorFactory.getJobExecutor(new TestSimpleJob(null),jobFacade),instanceOf(SimpleJobExecutor.class));
}
