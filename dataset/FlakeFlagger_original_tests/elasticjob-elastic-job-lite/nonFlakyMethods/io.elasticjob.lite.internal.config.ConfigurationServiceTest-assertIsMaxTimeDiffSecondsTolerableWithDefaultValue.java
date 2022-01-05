@Test public void assertIsMaxTimeDiffSecondsTolerableWithDefaultValue() throws JobExecutionEnvironmentException {
  when(jobNodeStorage.getJobNodeData(ConfigurationNode.ROOT)).thenReturn(LiteJsonConstants.getJobJson(-1));
  configService.checkMaxTimeDiffSecondsTolerable();
}
