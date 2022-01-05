@Deployment(resources={"org/activiti/engine/test/api/mgmt/ManagementServiceTest.testGetJobExceptionStacktrace.bpmn20.xml"}) public void testQueryByException(){
  TimerJobQuery query=managementService.createTimerJobQuery().withException();
  verifyQueryResults(query,0);
  ProcessInstance processInstance=startProcessInstanceWithFailingJob();
  query=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).withException();
  verifyFailedJob(query,processInstance);
}
