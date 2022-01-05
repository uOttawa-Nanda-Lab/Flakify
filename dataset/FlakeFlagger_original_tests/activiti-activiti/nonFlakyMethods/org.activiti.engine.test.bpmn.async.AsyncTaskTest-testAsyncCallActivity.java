@Deployment(resources={"org/activiti/engine/test/bpmn/async/AsyncTaskTest.testAsyncCallActivity.bpmn20.xml","org/activiti/engine/test/bpmn/async/AsyncTaskTest.testAsyncServiceNoListeners.bpmn20.xml"}) public void testAsyncCallActivity() throws Exception {
  runtimeService.startProcessInstanceByKey("asyncCallactivity");
  assertEquals(1,managementService.createJobQuery().count());
  waitForJobExecutorToProcessAllJobs(20000L,250L);
  assertEquals(0,managementService.createJobQuery().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
