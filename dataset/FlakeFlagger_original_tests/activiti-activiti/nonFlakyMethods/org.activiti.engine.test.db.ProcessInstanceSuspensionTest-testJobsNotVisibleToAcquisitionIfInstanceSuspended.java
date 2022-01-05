@Deployment(resources={"org/activiti/engine/test/db/oneJobProcess.bpmn20.xml"}) public void testJobsNotVisibleToAcquisitionIfInstanceSuspended(){
  ProcessDefinition pd=repositoryService.createProcessDefinitionQuery().singleResult();
  ProcessInstance pi=runtimeService.startProcessInstanceByKey(pd.getKey());
  Job job=managementService.createTimerJobQuery().singleResult();
  assertNotNull(job);
  makeSureJobDue(job);
  List<TimerJobEntity> acquiredJobs=executeAcquireJobsCommand();
  assertEquals(1,acquiredJobs.size());
  runtimeService.suspendProcessInstanceById(pi.getId());
  acquiredJobs=executeAcquireJobsCommand();
  assertEquals(0,acquiredJobs.size());
}
