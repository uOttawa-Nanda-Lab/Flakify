@Deployment public void testJobNotExecutedAfterProcessInstanceSuspend(){
  Date now=new Date();
  processEngineConfiguration.getClock().setCurrentTime(now);
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  ProcessInstance processInstance=runtimeService.startProcessInstanceById(processDefinition.getId());
  assertEquals(1,managementService.createTimerJobQuery().count());
  runtimeService.suspendProcessInstanceById(processInstance.getId());
  assertEquals(1,managementService.createSuspendedJobQuery().count());
  processEngineConfiguration.getClock().setCurrentTime(new Date(now.getTime() + (60 * 60 * 1000)));
  Job job=managementService.createTimerJobQuery().executable().singleResult();
  assertNull(job);
  assertEquals(1,managementService.createSuspendedJobQuery().count());
  runtimeService.activateProcessInstanceById(processInstance.getId());
  waitForJobExecutorToProcessAllJobs(10000L,100L);
  assertEquals(0,managementService.createJobQuery().count());
  assertEquals(0,managementService.createTimerJobQuery().count());
  assertEquals(0,managementService.createSuspendedJobQuery().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
