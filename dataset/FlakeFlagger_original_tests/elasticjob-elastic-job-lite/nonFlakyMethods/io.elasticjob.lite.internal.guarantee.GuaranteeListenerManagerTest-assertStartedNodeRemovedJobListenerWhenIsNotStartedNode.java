@Test public void assertStartedNodeRemovedJobListenerWhenIsNotStartedNode(){
  guaranteeListenerManager.new StartedNodeRemovedJobListener().dataChanged("/other_job/guarantee/started",Type.NODE_REMOVED,"");
  verify(distributeOnceElasticJobListener,times(0)).notifyWaitingTaskStart();
}
