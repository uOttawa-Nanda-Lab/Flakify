@Test @Deployment(resources={"org/activiti/engine/test/bpmn/async/AsyncTaskTest.testAsyncTask.bpmn20.xml"}) public void testWaitForJobs(){
  RuntimeService runtimeService=activitiRule.getRuntimeService();
  ManagementService managementService=activitiRule.getManagementService();
  runtimeService.startProcessInstanceByKey("asyncTask");
  assertEquals(1,managementService.createJobQuery().count());
  JobTestHelper.waitForJobExecutorToProcessAllJobs(activitiRule,5000L,500L);
  assertEquals(0,managementService.createJobQuery().count());
}
