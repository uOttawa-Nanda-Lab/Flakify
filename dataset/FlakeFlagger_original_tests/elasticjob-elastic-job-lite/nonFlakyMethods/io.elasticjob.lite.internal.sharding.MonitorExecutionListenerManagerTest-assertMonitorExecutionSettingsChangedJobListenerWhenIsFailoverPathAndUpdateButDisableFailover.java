@Test public void assertMonitorExecutionSettingsChangedJobListenerWhenIsFailoverPathAndUpdateButDisableFailover(){
  monitorExecutionListenerManager.new MonitorExecutionSettingsChangedJobListener().dataChanged("/test_job/config",TreeCacheEvent.Type.NODE_UPDATED,LiteJsonConstants.getJobJsonWithMonitorExecution(false));
  verify(executionService).clearAllRunningInfo();
}
