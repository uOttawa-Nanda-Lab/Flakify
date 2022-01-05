@Test public void assertListenServersChangedJobListenerWhenIsServerStatusPathButUpdate(){
  shardingListenerManager.new ListenServersChangedJobListener().dataChanged("/test_job/servers/127.0.0.1/status",Type.NODE_UPDATED,"");
  verify(shardingService,times(0)).setReshardingFlag();
}
