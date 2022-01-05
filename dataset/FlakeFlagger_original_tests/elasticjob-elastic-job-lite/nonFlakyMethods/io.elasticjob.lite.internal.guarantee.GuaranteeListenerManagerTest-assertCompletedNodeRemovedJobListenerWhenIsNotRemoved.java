@Test public void assertCompletedNodeRemovedJobListenerWhenIsNotRemoved(){
  guaranteeListenerManager.new CompletedNodeRemovedJobListener().dataChanged("/test_job/guarantee/completed",Type.NODE_UPDATED,"");
  verify(distributeOnceElasticJobListener,times(0)).notifyWaitingTaskStart();
}
