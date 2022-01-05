@Test(expected=JobSystemException.class) public void assertExecuteWhenCheckMaxTimeDiffSecondsIntolerable() throws JobExecutionEnvironmentException {
  doThrow(JobExecutionEnvironmentException.class).when(jobFacade).checkJobExecutionEnvironment();
  try {
    simpleJobExecutor.execute();
  }
  finally {
    verify(jobFacade).checkJobExecutionEnvironment();
    verify(jobCaller,times(0)).execute();
  }
}
