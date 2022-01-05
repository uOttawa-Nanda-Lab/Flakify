@Test(expected=JobExecutionEnvironmentException.class) public void assertIsNotMaxTimeDiffSecondsTolerable() throws JobExecutionEnvironmentException {
  when(jobNodeStorage.getJobNodeData(ConfigurationNode.ROOT)).thenReturn(LiteJsonConstants.getJobJson());
  when(jobNodeStorage.getRegistryCenterTime()).thenReturn(0L);
  try {
    configService.checkMaxTimeDiffSecondsTolerable();
  }
  finally {
    verify(jobNodeStorage).getRegistryCenterTime();
  }
}
