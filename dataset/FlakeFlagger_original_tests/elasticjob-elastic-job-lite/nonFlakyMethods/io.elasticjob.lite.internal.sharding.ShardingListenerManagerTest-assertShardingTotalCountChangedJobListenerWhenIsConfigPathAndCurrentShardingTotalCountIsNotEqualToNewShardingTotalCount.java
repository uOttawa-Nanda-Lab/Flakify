@Test public void assertShardingTotalCountChangedJobListenerWhenIsConfigPathAndCurrentShardingTotalCountIsNotEqualToNewShardingTotalCount() throws NoSuchFieldException {
  JobRegistry.getInstance().setCurrentShardingTotalCount("test_job",5);
  shardingListenerManager.new ShardingTotalCountChangedJobListener().dataChanged("/test_job/config",Type.NODE_UPDATED,LiteJsonConstants.getJobJson());
  verify(shardingService).setReshardingFlag();
  JobRegistry.getInstance().setCurrentShardingTotalCount("test_job",0);
}
