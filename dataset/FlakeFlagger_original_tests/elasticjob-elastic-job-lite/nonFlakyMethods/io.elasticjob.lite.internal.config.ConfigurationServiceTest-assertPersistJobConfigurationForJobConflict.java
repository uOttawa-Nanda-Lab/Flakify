@Test(expected=JobConfigurationException.class) public void assertPersistJobConfigurationForJobConflict(){
  when(jobNodeStorage.isJobNodeExisted(ConfigurationNode.ROOT)).thenReturn(true);
  when(jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.ROOT)).thenReturn(LiteJsonConstants.getJobJson("io.elasticjob.lite.api.script.api.ScriptJob"));
  try {
    configService.persist(JobConfigurationUtil.createSimpleLiteJobConfiguration());
  }
  finally {
    verify(jobNodeStorage).isJobNodeExisted(ConfigurationNode.ROOT);
    verify(jobNodeStorage).getJobNodeDataDirectly(ConfigurationNode.ROOT);
  }
}
