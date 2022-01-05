@Test public void assertGetJobExecutorForScriptJob(){
  when(jobFacade.loadJobRootConfiguration(true)).thenReturn(new TestScriptJobConfiguration("test.sh",IgnoreJobExceptionHandler.class));
  assertThat(JobExecutorFactory.getJobExecutor(null,jobFacade),instanceOf(ScriptJobExecutor.class));
}
