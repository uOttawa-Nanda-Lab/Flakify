@Deployment(resources={"org/activiti/engine/test/api/mgmt/ManagementServiceTest.testGetJobExceptionStacktrace.bpmn20.xml"}) public void testQueryByExceptionMessageEmpty(){
  JobQuery query=managementService.createJobQuery().exceptionMessage("");
  verifyQueryResults(query,0);
  startProcessInstanceWithFailingJob();
  query=managementService.createJobQuery().exceptionMessage("");
  verifyQueryResults(query,0);
}
