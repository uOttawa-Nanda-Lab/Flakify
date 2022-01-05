@Test public void assertFailoverSettingsChangedJobListenerWhenIsFailoverPathAndUpdateButDisableFailover(){
  failoverListenerManager.new FailoverSettingsChangedJobListener().dataChanged("/test_job/config",Type.NODE_UPDATED,LiteJsonConstants.getJobJsonWithFailover(false));
  verify(failoverService).removeFailoverInfo();
}
