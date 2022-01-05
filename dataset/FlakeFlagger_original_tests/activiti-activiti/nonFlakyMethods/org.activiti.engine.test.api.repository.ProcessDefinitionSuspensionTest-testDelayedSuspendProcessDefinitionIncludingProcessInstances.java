@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testDelayedSuspendProcessDefinitionIncludingProcessInstances(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  Date startTime=new Date();
  processEngineConfiguration.getClock().setCurrentTime(startTime);
  int nrOfProcessInstances=30;
  for (int i=0; i < nrOfProcessInstances; i++) {
    runtimeService.startProcessInstanceById(processDefinition.getId());
  }
  assertEquals(nrOfProcessInstances,runtimeService.createProcessInstanceQuery().count());
  assertEquals(nrOfProcessInstances,runtimeService.createProcessInstanceQuery().active().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().suspended().count());
  assertEquals(0,taskService.createTaskQuery().suspended().count());
  assertEquals(nrOfProcessInstances,taskService.createTaskQuery().active().count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().active().count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().suspended().count());
  long oneWeekFromStartTime=startTime.getTime() + (7 * 24 * 60* 60* 1000);
  repositoryService.suspendProcessDefinitionById(processDefinition.getId(),true,new Date(oneWeekFromStartTime));
  runtimeService.startProcessInstanceById(processDefinition.getId());
  nrOfProcessInstances=nrOfProcessInstances + 1;
  assertEquals(nrOfProcessInstances,runtimeService.createProcessInstanceQuery().count());
  assertEquals(1,managementService.createTimerJobQuery().processDefinitionId(processDefinition.getId()).count());
  long eightDaysSinceStartTime=oneWeekFromStartTime + (2 * 24 * 60* 60* 1000);
  processEngineConfiguration.getClock().setCurrentTime(new Date(eightDaysSinceStartTime));
  waitForJobExecutorToProcessAllJobs(30000L,50L);
  assertEquals(0,managementService.createJobQuery().processDefinitionId(processDefinition.getId()).count());
  assertEquals(0,managementService.createTimerJobQuery().processDefinitionId(processDefinition.getId()).count());
  try {
    runtimeService.startProcessInstanceById(processDefinition.getId());
    fail();
  }
 catch (  ActivitiException e) {
    assertTextPresentIgnoreCase("suspended",e.getMessage());
  }
  assertEquals(nrOfProcessInstances,runtimeService.createProcessInstanceQuery().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().active().count());
  assertEquals(nrOfProcessInstances,runtimeService.createProcessInstanceQuery().suspended().count());
  assertEquals(nrOfProcessInstances,taskService.createTaskQuery().suspended().count());
  assertEquals(0,taskService.createTaskQuery().active().count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().active().count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().suspended().count());
  repositoryService.activateProcessDefinitionById(processDefinition.getId(),true,null);
  assertEquals(nrOfProcessInstances,runtimeService.createProcessInstanceQuery().count());
  assertEquals(nrOfProcessInstances,runtimeService.createProcessInstanceQuery().active().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().suspended().count());
  assertEquals(0,taskService.createTaskQuery().suspended().count());
  assertEquals(nrOfProcessInstances,taskService.createTaskQuery().active().count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().active().count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().suspended().count());
}
