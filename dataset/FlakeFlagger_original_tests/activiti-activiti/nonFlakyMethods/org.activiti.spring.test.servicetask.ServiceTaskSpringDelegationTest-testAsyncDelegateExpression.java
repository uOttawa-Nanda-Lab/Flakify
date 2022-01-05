@Deployment public void testAsyncDelegateExpression() throws Exception {
  ProcessInstance procInst=runtimeService.startProcessInstanceByKey("delegateExpressionToSpringBean");
  assertTrue(JobTestHelper.areJobsAvailable(managementService));
  waitForJobExecutorToProcessAllJobs(5000,500);
  Thread.sleep(1000);
  assertEquals("Activiti BPMN 2.0 process engine",runtimeService.getVariable(procInst.getId(),"myVar"));
  assertEquals("fieldInjectionWorking",runtimeService.getVariable(procInst.getId(),"fieldInjection"));
}
