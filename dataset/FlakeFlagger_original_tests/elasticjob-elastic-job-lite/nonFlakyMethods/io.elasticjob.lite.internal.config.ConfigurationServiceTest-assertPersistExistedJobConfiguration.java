@Test public void assertPersistExistedJobConfiguration() throws NoSuchFieldException {
  when(jobNodeStorage.isJobNodeExisted(ConfigurationNode.ROOT)).thenReturn(true);
  when(jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.ROOT)).thenReturn(LiteJsonConstants.getJobJson());
  LiteJobConfiguration liteJobConfig=JobConfigurationUtil.createSimpleLiteJobConfiguration(true);
  configService.persist(liteJobConfig);
  verify(jobNodeStorage).replaceJobNode("config",LiteJobConfigurationGsonFactory.toJson(liteJobConfig));
}
