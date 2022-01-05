@Test public void assertPersistNewJobConfiguration(){
  LiteJobConfiguration liteJobConfig=JobConfigurationUtil.createSimpleLiteJobConfiguration();
  configService.persist(liteJobConfig);
  verify(jobNodeStorage).replaceJobNode("config",LiteJobConfigurationGsonFactory.toJson(liteJobConfig));
}
