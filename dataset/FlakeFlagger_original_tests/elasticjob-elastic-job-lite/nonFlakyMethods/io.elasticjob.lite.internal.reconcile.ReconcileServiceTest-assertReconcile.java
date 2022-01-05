@Test public void assertReconcile() throws Exception {
  Mockito.when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).reconcileIntervalMinutes(1).build());
  Mockito.when(shardingService.isNeedSharding()).thenReturn(false);
  Mockito.when(shardingService.hasShardingInfoInOfflineServers()).thenReturn(true);
  Mockito.when(leaderService.isLeaderUntilBlock()).thenReturn(true);
  reconcileService.runOneIteration();
  Mockito.verify(shardingService).isNeedSharding();
  Mockito.verify(shardingService).hasShardingInfoInOfflineServers();
  Mockito.verify(shardingService).setReshardingFlag();
  Mockito.verify(leaderService).isLeaderUntilBlock();
}
