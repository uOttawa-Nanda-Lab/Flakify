@Test public void assertLoadDirectly(){
  when(jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.ROOT)).thenReturn(LiteJsonConstants.getJobJson());
  LiteJobConfiguration actual=configService.load(false);
  assertThat(actual.getJobName(),is("test_job"));
  assertThat(actual.getTypeConfig().getCoreConfig().getCron(),is("0/1 * * * * ?"));
  assertThat(actual.getTypeConfig().getCoreConfig().getShardingTotalCount(),is(3));
}
