@Test public void assertLoadFromCacheButNull(){
  when(jobNodeStorage.getJobNodeData(ConfigurationNode.ROOT)).thenReturn(null);
  when(jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.ROOT)).thenReturn(LiteJsonConstants.getJobJson());
  LiteJobConfiguration actual=configService.load(true);
  assertThat(actual.getJobName(),is("test_job"));
  assertThat(actual.getTypeConfig().getCoreConfig().getCron(),is("0/1 * * * * ?"));
  assertThat(actual.getTypeConfig().getCoreConfig().getShardingTotalCount(),is(3));
}
