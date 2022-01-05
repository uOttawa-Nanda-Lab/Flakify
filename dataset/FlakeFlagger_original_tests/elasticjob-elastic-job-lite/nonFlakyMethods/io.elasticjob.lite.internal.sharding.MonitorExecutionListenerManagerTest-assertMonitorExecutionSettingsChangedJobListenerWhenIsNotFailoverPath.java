@Test public void assertMonitorExecutionSettingsChangedJobListenerWhenIsNotFailoverPath(){
  monitorExecutionListenerManager.new MonitorExecutionSettingsChangedJobListener().dataChanged("/test_job/other",TreeCacheEvent.Type.NODE_ADDED,LiteJsonConstants.getJobJson());
  verify(executionService,times(0)).clearAllRunningInfo();
}
