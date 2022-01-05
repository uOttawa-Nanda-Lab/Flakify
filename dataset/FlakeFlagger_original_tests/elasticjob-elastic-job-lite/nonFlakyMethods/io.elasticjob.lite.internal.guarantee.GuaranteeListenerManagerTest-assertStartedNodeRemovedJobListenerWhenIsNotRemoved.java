@Test public void assertStartedNodeRemovedJobListenerWhenIsNotRemoved(){
  guaranteeListenerManager.new StartedNodeRemovedJobListener().dataChanged("/test_job/guarantee/started",Type.NODE_UPDATED,"");
  verify(distributeOnceElasticJobListener,times(0)).notifyWaitingTaskStart();
}
