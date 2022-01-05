@Test public void testAsyncScriptExecution(){
  ProcessEngine processEngine=null;
  try {
    processEngine=createProcessEngine(true);
    setClockToCurrentTime(processEngine);
    deploy(processEngine,"AsyncExecutorTest.testAsyncScriptExecution.bpmn20.xml");
    ProcessInstance processInstance=processEngine.getRuntimeService().startProcessInstanceByKey("asyncScript");
    waitForAllJobsBeingExecuted(processEngine);
    Assert.assertEquals(0,processEngine.getManagementService().createJobQuery().count());
    Assert.assertEquals(0,processEngine.getManagementService().createTimerJobQuery().count());
    Assert.assertEquals(1,processEngine.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).count());
    Assert.assertEquals(1,processEngine.getTaskService().createTaskQuery().taskName("Task after script").count());
    Assert.assertEquals(1,getAsyncExecutorJobCount(processEngine));
  }
  finally {
    cleanup(processEngine);
  }
}
