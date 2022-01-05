@Deployment(resources={"org/activiti/engine/test/db/oneJobProcess.bpmn20.xml"}) public void testJobsVisibleToAcquisitionIfDefinitionSuspendedWithoutProcessInstances(){
  ProcessDefinition pd=repositoryService.createProcessDefinitionQuery().singleResult();
  runtimeService.startProcessInstanceByKey(pd.getKey());
  Job job=managementService.createTimerJobQuery().singleResult();
  assertNotNull(job);
  makeSureJobDue(job);
  List<TimerJobEntity> acquiredJobs=executeAcquireJobsCommand();
  assertEquals(1,acquiredJobs.size());
  repositoryService.suspendProcessDefinitionById(pd.getId());
  acquiredJobs=executeAcquireJobsCommand();
  assertEquals(1,acquiredJobs.size());
}
