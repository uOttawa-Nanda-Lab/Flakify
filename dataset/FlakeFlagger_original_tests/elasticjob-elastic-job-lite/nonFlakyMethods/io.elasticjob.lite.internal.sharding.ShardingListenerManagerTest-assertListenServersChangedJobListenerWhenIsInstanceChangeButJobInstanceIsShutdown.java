@Test public void assertListenServersChangedJobListenerWhenIsInstanceChangeButJobInstanceIsShutdown(){
  shardingListenerManager.new ListenServersChangedJobListener().dataChanged("/test_job/instances/xxx",Type.NODE_ADDED,"");
  verify(shardingService,times(0)).setReshardingFlag();
}
