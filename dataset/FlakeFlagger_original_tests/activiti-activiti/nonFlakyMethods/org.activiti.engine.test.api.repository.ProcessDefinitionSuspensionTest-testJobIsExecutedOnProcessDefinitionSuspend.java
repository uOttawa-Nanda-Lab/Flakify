@Deployment public void testJobIsExecutedOnProcessDefinitionSuspend(){
  Date now=new Date();
  processEngineConfiguration.getClock().setCurrentTime(now);
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  runtimeService.startProcessInstanceById(processDefinition.getId());
  repositoryService.suspendProcessDefinitionById(processDefinition.getId());
  assertEquals(1,managementService.createTimerJobQuery().count());
  processEngineConfiguration.getClock().setCurrentTime(new Date(now.getTime() + (60 * 60 * 1000)));
  waitForJobExecutorToProcessAllJobs(2000L,100L);
  assertEquals(0,managementService.createJobQuery().count());
  assertEquals(0,managementService.createTimerJobQuery().count());
}
