@Test public void assertFailoverSettingsChangedJobListenerWhenIsFailoverPathAndUpdateButEnableFailover(){
  failoverListenerManager.new FailoverSettingsChangedJobListener().dataChanged("/test_job/config",Type.NODE_UPDATED,LiteJsonConstants.getJobJson());
  verify(failoverService,times(0)).removeFailoverInfo();
}
