@Test public void assertCheckMaxTimeDiffSecondsTolerable() throws JobExecutionEnvironmentException {
  liteJobFacade.checkJobExecutionEnvironment();
  verify(configService).checkMaxTimeDiffSecondsTolerable();
}
