@Test public void assertStartedNodeRemovedJobListenerWhenIsRemovedAndStartedNode(){
  guaranteeListenerManager.new StartedNodeRemovedJobListener().dataChanged("/test_job/guarantee/started",Type.NODE_REMOVED,"");
  verify(distributeOnceElasticJobListener).notifyWaitingTaskStart();
}
