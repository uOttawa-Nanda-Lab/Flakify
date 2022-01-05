@Test public void testRegularAsyncExecution(){
  ProcessEngine processEngine=null;
  try {
    processEngine=createProcessEngine(true);
    setClockToCurrentTime(processEngine);
    deploy(processEngine,"AsyncExecutorTest.testRegularAsyncExecution.bpmn20.xml");
    processEngine.getRuntimeService().startProcessInstanceByKey("asyncExecutor");
    addSecondsToCurrentTime(processEngine,180L);
    try {
      waitForAllJobsBeingExecuted(processEngine,500L);
      Assert.fail();
    }
 catch (    ActivitiException e) {
    }
    Assert.assertEquals(1,processEngine.getTaskService().createTaskQuery().taskName("The Task").count());
    Assert.assertEquals(0,processEngine.getTaskService().createTaskQuery().taskName("Task after timer").count());
    Assert.assertEquals(1,processEngine.getManagementService().createTimerJobQuery().count());
    Assert.assertEquals(0,getAsyncExecutorJobCount(processEngine));
    addSecondsToCurrentTime(processEngine,181);
    waitForAllJobsBeingExecuted(processEngine);
    Assert.assertEquals(0,processEngine.getTaskService().createTaskQuery().taskName("The Task").count());
    Assert.assertEquals(1,processEngine.getTaskService().createTaskQuery().taskName("Task after timer").count());
    Assert.assertEquals(0,processEngine.getManagementService().createTimerJobQuery().count());
    Assert.assertEquals(0,processEngine.getManagementService().createJobQuery().count());
    Assert.assertEquals(1,getAsyncExecutorJobCount(processEngine));
  }
  finally {
    if (processEngine != null) {
      cleanup(processEngine);
    }
  }
}
