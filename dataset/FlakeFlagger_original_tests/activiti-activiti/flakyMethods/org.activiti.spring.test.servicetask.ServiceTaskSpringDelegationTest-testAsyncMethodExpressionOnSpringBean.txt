@Deployment public void testAsyncMethodExpressionOnSpringBean(){
  ProcessInstance procInst=runtimeService.startProcessInstanceByKey("methodExpressionOnSpringBean");
  assertTrue(JobTestHelper.areJobsAvailable(managementService));
  waitForJobExecutorToProcessAllJobs(5000,500);
  assertEquals("ACTIVITI BPMN 2.0 PROCESS ENGINE",runtimeService.getVariable(procInst.getId(),"myVar"));
}
