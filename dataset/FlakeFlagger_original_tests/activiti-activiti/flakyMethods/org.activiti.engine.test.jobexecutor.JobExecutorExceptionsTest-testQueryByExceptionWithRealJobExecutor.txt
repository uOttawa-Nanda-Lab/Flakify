@Test @Deployment(resources={"org/activiti/engine/test/api/mgmt/ManagementServiceTest.testGetJobExceptionStacktrace.bpmn20.xml"}) public void testQueryByExceptionWithRealJobExecutor(){
  TimerJobQuery query=managementService.createTimerJobQuery().withException();
  Assert.assertEquals(0,query.count());
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("exceptionInJobExecution");
  processEngineConfiguration.getClock().setCurrentTime(new Date(new Date().getTime() + 5 * 60 * 60* 1000));
  JobTestHelper.waitForJobExecutorOnCondition(processEngineConfiguration,5000L,100L,new Callable<Boolean>(){
    public Boolean call() throws Exception {
      return managementService.createTimerJobQuery().withException().count() == 1;
    }
  }
);
  query=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).withException();
  Assert.assertEquals(1,query.count());
}
