@Deployment(resources={"org/activiti/engine/test/api/mgmt/ManagementServiceTest.testGetJobExceptionStacktrace.bpmn20.xml"}) public void testQueryByExceptionMessage(){
  TimerJobQuery query=managementService.createTimerJobQuery().exceptionMessage(EXCEPTION_MESSAGE);
  verifyQueryResults(query,0);
  ProcessInstance processInstance=startProcessInstanceWithFailingJob();
  query=managementService.createTimerJobQuery().exceptionMessage(EXCEPTION_MESSAGE);
  verifyFailedJob(query,processInstance);
}
