@Test public void assertHasNotShardingInfoInOfflineServers(){
  when(jobNodeStorage.getJobNodeChildrenKeys(InstanceNode.ROOT)).thenReturn(Arrays.asList("host0@-@0","host0@-@1"));
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).build());
  when(jobNodeStorage.getJobNodeData(ShardingNode.getInstanceNode(0))).thenReturn("host0@-@0");
  when(jobNodeStorage.getJobNodeData(ShardingNode.getInstanceNode(1))).thenReturn("host0@-@1");
  when(jobNodeStorage.getJobNodeData(ShardingNode.getInstanceNode(2))).thenReturn("host0@-@0");
  assertFalse(shardingService.hasShardingInfoInOfflineServers());
}
