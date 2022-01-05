@Test public void assertCompletedNodeRemovedJobListenerWhenIsNotCompletedNode(){
  guaranteeListenerManager.new CompletedNodeRemovedJobListener().dataChanged("/other_job/guarantee/completed",Type.NODE_REMOVED,"");
  verify(distributeOnceElasticJobListener,times(0)).notifyWaitingTaskStart();
}
