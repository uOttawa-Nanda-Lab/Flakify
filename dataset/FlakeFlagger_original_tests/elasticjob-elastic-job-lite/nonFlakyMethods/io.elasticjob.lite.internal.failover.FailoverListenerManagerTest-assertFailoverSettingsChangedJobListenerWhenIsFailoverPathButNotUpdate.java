@Test public void assertFailoverSettingsChangedJobListenerWhenIsFailoverPathButNotUpdate(){
  failoverListenerManager.new FailoverSettingsChangedJobListener().dataChanged("/test_job/config",Type.NODE_ADDED,"");
  verify(failoverService,times(0)).removeFailoverInfo();
}
