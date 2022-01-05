@Test public void assertCompletedNodeRemovedJobListenerWhenIsRemovedAndCompletedNode(){
  guaranteeListenerManager.new CompletedNodeRemovedJobListener().dataChanged("/test_job/guarantee/completed",Type.NODE_REMOVED,"");
  verify(distributeOnceElasticJobListener).notifyWaitingTaskComplete();
}
