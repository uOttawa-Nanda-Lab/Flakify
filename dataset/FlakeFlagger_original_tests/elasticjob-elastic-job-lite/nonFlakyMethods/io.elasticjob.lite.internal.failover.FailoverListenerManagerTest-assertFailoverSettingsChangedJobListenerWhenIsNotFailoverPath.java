@Test public void assertFailoverSettingsChangedJobListenerWhenIsNotFailoverPath(){
  failoverListenerManager.new FailoverSettingsChangedJobListener().dataChanged("/test_job/other",Type.NODE_ADDED,LiteJsonConstants.getJobJson());
  verify(failoverService,times(0)).removeFailoverInfo();
}
