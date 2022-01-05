@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testDelayedSuspendProcessDefinition(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  Date startTime=new Date();
  processEngineConfiguration.getClock().setCurrentTime(startTime);
  long oneWeekFromStartTime=startTime.getTime() + (7 * 24 * 60* 60* 1000);
  repositoryService.suspendProcessDefinitionById(processDefinition.getId(),false,new Date(oneWeekFromStartTime));
  runtimeService.startProcessInstanceById(processDefinition.getId());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().active().count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().suspended().count());
  assertEquals(1,managementService.createTimerJobQuery().processDefinitionId(processDefinition.getId()).count());
  long eightDaysSinceStartTime=oneWeekFromStartTime + (24 * 60 * 60* 1000);
  processEngineConfiguration.getClock().setCurrentTime(new Date(eightDaysSinceStartTime));
  waitForJobExecutorToProcessAllJobs(5000L,50L);
  assertEquals(0,managementService.createJobQuery().processDefinitionId(processDefinition.getId()).count());
  assertEquals(0,managementService.createTimerJobQuery().processDefinitionId(processDefinition.getId()).count());
  try {
    runtimeService.startProcessInstanceById(processDefinition.getId());
    fail();
  }
 catch (  ActivitiException e) {
    assertTextPresentIgnoreCase("suspended",e.getMessage());
  }
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().active().count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().suspended().count());
  repositoryService.activateProcessDefinitionById(processDefinition.getId());
  runtimeService.startProcessInstanceById(processDefinition.getId());
  assertEquals(2,runtimeService.createProcessInstanceQuery().count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().active().count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().suspended().count());
}
