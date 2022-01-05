@Test public void assertListenServersChangedJobListenerWhenIsInstanceChange(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  shardingListenerManager.new ListenServersChangedJobListener().dataChanged("/test_job/instances/xxx",Type.NODE_ADDED,"");
  verify(shardingService).setReshardingFlag();
  JobRegistry.getInstance().shutdown("test_job");
}
