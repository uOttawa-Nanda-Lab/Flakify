@Test @Deployment(resources={"org/activiti/engine/test/concurrency/CompetingJoinTest.testCompetingJoins.bpmn20.xml"}) public void testOptimisticLockExceptionForConcurrentJoin() throws Exception {
  boolean optimisticLockingExceptionHappenedOnce=false;
  for (int i=0; i < 10; i++) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("CompetingJoinsProcess");
    Execution execution1=runtimeService.createExecutionQuery().activityId("wait1").processInstanceId(processInstance.getId()).singleResult();
    Execution execution2=runtimeService.createExecutionQuery().activityId("wait2").processInstanceId(processInstance.getId()).singleResult();
    TestTriggerableThread t1=new TestTriggerableThread(processEngine,execution1.getId());
    TestTriggerableThread t2=new TestTriggerableThread(processEngine,execution2.getId());
    t1.start();
    t2.start();
    OptimisticLockingTestCommandContextCloseListener.TEST_BARRIER_BEFORE_CLOSE.await();
    long totalWaitTime=0;
    while (t1.getException() == null && t2.getException() == null && runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).count() == 1) {
      Thread.sleep(250L);
      totalWaitTime+=250L;
      if (totalWaitTime >= 5000L) {
        break;
      }
    }
    if ((t1.getException() != null && t1.getException() instanceof ActivitiOptimisticLockingException) || (t2.getException() != null && t2.getException() instanceof ActivitiOptimisticLockingException)) {
      optimisticLockingExceptionHappenedOnce=true;
      break;
    }
    boolean processInstanceEnded=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).count() == 0;
    Assert.assertTrue(processInstanceEnded);
  }
  Assert.assertTrue(optimisticLockingExceptionHappenedOnce);
}
