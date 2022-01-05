@Test public void assertJobCrashedJobListenerWhenFailoverDisabled(){
  failoverListenerManager.new JobCrashedJobListener().dataChanged("/test_job/instances/127.0.0.1@-@0",Type.NODE_REMOVED,"");
  verify(failoverService,times(0)).failoverIfNecessary();
}
