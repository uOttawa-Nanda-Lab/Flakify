@Test public void testAsyncFailingScript(){
  ProcessEngine processEngine=null;
  try {
    processEngine=createProcessEngine(true);
    processEngine.getProcessEngineConfiguration().getClock().reset();
    deploy(processEngine,"AsyncExecutorTest.testAsyncFailingScript.bpmn20.xml");
    processEngine.getProcessEngineConfiguration().setAsyncFailedJobWaitTime(1);
    processEngine.getProcessEngineConfiguration().setDefaultFailedJobWaitTime(1);
    processEngine.getRuntimeService().startProcessInstanceByKey("asyncScript");
    final ProcessEngine processEngineCopy=processEngine;
    JobTestHelper.waitForJobExecutorOnCondition(processEngine.getProcessEngineConfiguration(),10000L,1000L,new Callable<Boolean>(){
      @Override public Boolean call() throws Exception {
        long timerJobCount=processEngineCopy.getManagementService().createTimerJobQuery().count();
        if (timerJobCount == 0) {
          return processEngineCopy.getManagementService().createJobQuery().count() == 0;
        }
 else {
          return false;
        }
      }
    }
);
    Assert.assertEquals(0,processEngine.getTaskService().createTaskQuery().taskName("Task after script").count());
    Assert.assertEquals(0,processEngine.getManagementService().createJobQuery().count());
    Assert.assertEquals(1,processEngine.getManagementService().createDeadLetterJobQuery().count());
    Assert.assertEquals(3,getAsyncExecutorJobCount(processEngine));
  }
  finally {
    cleanup(processEngine);
  }
}
