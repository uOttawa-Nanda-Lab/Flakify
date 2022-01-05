@Test public void assertShardingTotalCountChangedJobListenerWhenIsConfigPathAndCurrentShardingTotalCountIsEqualToNewShardingTotalCount(){
  JobRegistry.getInstance().setCurrentShardingTotalCount("test_job",3);
  shardingListenerManager.new ShardingTotalCountChangedJobListener().dataChanged("/test_job/config",Type.NODE_ADDED,LiteJsonConstants.getJobJson());
  verify(shardingService,times(0)).setReshardingFlag();
  JobRegistry.getInstance().setCurrentShardingTotalCount("test_job",0);
}
