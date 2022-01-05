@Test public void assertListenServersChangedJobListenerWhenIsServerChange(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  shardingListenerManager.new ListenServersChangedJobListener().dataChanged("/test_job/servers/127.0.0.1",Type.NODE_UPDATED,"");
  verify(shardingService).setReshardingFlag();
  JobRegistry.getInstance().shutdown("test_job");
}
