@Test public void assertMonitorExecutionSettingsChangedJobListenerWhenIsFailoverPathAndUpdateButEnableFailover(){
  monitorExecutionListenerManager.new MonitorExecutionSettingsChangedJobListener().dataChanged("/test_job/config",TreeCacheEvent.Type.NODE_UPDATED,LiteJsonConstants.getJobJson());
  verify(executionService,times(0)).clearAllRunningInfo();
}
