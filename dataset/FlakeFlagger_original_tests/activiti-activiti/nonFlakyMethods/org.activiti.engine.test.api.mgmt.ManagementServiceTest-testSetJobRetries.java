@Deployment(resources={"org/activiti/engine/test/api/mgmt/ManagementServiceTest.testGetJobExceptionStacktrace.bpmn20.xml"}) public void testSetJobRetries(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("exceptionInJobExecution");
  Job timerJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  Date duedate=timerJob.getDuedate();
  assertNotNull("No job found for process instance",timerJob);
  assertEquals(processEngineConfiguration.getAsyncExecutorNumberOfRetries(),timerJob.getRetries());
  managementService.setTimerJobRetries(timerJob.getId(),5);
  timerJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals(5,timerJob.getRetries());
  assertEquals(duedate,timerJob.getDuedate());
}
