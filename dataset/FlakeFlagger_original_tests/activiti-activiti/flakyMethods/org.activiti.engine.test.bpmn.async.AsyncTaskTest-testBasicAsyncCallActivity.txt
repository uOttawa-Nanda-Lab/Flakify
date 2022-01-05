@Deployment(resources={"org/activiti/engine/test/bpmn/async/AsyncTaskTest.testBasicAsyncCallActivity.bpmn20.xml","org/activiti/engine/test/bpmn/StartToEndTest.testStartToEnd.bpmn20.xml"}) public void testBasicAsyncCallActivity(){
  runtimeService.startProcessInstanceByKey("myProcess");
  Assert.assertEquals("There should be one job available.",1,managementService.createJobQuery().count());
  waitForJobExecutorToProcessAllJobs(5000L,250L);
  assertEquals(0,managementService.createJobQuery().count());
}
