@Test public void assertIsMaxTimeDiffSecondsTolerable() throws JobExecutionEnvironmentException {
  when(jobNodeStorage.getJobNodeData(ConfigurationNode.ROOT)).thenReturn(LiteJsonConstants.getJobJson());
  when(jobNodeStorage.getRegistryCenterTime()).thenReturn(System.currentTimeMillis());
  configService.checkMaxTimeDiffSecondsTolerable();
  verify(jobNodeStorage).getRegistryCenterTime();
}
