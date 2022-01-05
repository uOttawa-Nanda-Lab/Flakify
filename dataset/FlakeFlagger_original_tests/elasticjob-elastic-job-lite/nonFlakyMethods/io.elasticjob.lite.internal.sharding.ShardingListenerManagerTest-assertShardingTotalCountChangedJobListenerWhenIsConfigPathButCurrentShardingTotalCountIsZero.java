@Test public void assertShardingTotalCountChangedJobListenerWhenIsConfigPathButCurrentShardingTotalCountIsZero(){
  shardingListenerManager.new ShardingTotalCountChangedJobListener().dataChanged("/test_job/config",Type.NODE_ADDED,LiteJsonConstants.getJobJson());
  verify(shardingService,times(0)).setReshardingFlag();
}
