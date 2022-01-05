@Test public void assertMonitorExecutionSettingsChangedJobListenerWhenIsFailoverPathButNotUpdate(){
  monitorExecutionListenerManager.new MonitorExecutionSettingsChangedJobListener().dataChanged("/test_job/config",TreeCacheEvent.Type.NODE_ADDED,"");
  verify(executionService,times(0)).clearAllRunningInfo();
}
