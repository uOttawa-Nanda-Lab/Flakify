@Test public void testAsyncExecutorDisabledOnOneEngine(){
  ProcessEngine firstProcessEngine=null;
  ProcessEngine secondProcessEngine=null;
  try {
    firstProcessEngine=createProcessEngine(false);
    Date now=setClockToCurrentTime(firstProcessEngine);
    deploy(firstProcessEngine,"AsyncExecutorTest.testRegularAsyncExecution.bpmn20.xml");
    firstProcessEngine.getRuntimeService().startProcessInstanceByKey("asyncExecutor");
    addSecondsToCurrentTime(firstProcessEngine,301);
    Assert.assertEquals(1,firstProcessEngine.getTaskService().createTaskQuery().taskName("The Task").count());
    Assert.assertEquals(0,firstProcessEngine.getTaskService().createTaskQuery().taskName("Task after timer").count());
    Assert.assertEquals(1,firstProcessEngine.getManagementService().createTimerJobQuery().count());
    secondProcessEngine=createProcessEngine(true,now);
    addSecondsToCurrentTime(secondProcessEngine,361);
    waitForAllJobsBeingExecuted(secondProcessEngine);
    Assert.assertEquals(0,firstProcessEngine.getTaskService().createTaskQuery().taskName("The Task").count());
    Assert.assertEquals(1,firstProcessEngine.getTaskService().createTaskQuery().taskName("Task after timer").count());
    Assert.assertEquals(0,firstProcessEngine.getManagementService().createTimerJobQuery().count());
    Assert.assertEquals(0,firstProcessEngine.getManagementService().createJobQuery().count());
    Assert.assertEquals(0,getAsyncExecutorJobCount(firstProcessEngine));
    Assert.assertEquals(1,getAsyncExecutorJobCount(secondProcessEngine));
  }
  finally {
    cleanup(firstProcessEngine);
    cleanup(secondProcessEngine);
  }
}
