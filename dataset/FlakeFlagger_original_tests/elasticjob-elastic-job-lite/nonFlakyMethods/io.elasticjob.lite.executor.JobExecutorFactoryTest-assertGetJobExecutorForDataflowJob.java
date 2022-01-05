@Test public void assertGetJobExecutorForDataflowJob(){
  when(jobFacade.loadJobRootConfiguration(true)).thenReturn(new TestDataflowJobConfiguration(false));
  assertThat(JobExecutorFactory.getJobExecutor(new TestDataflowJob(null),jobFacade),instanceOf(DataflowJobExecutor.class));
}
