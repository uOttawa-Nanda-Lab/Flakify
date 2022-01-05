@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testDelayedActivateProcessDefinition(){
  Date startTime=new Date();
  processEngineConfiguration.getClock().setCurrentTime(startTime);
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  repositoryService.suspendProcessDefinitionById(processDefinition.getId());
  try {
    runtimeService.startProcessInstanceById(processDefinition.getId());
    fail();
  }
 catch (  ActivitiException e) {
    assertTextPresentIgnoreCase("suspended",e.getMessage());
  }
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().active().count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().suspended().count());
  long oneDayFromStart=startTime.getTime() + (24 * 60 * 60* 1000);
  repositoryService.activateProcessDefinitionById(processDefinition.getId(),false,new Date(oneDayFromStart));
  long twoDaysFromStart=startTime.getTime() + (2 * 24 * 60* 60* 1000);
  processEngineConfiguration.getClock().setCurrentTime(new Date(twoDaysFromStart));
  waitForJobExecutorToProcessAllJobs(5000L,50L);
  runtimeService.startProcessInstanceById(processDefinition.getId());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().active().count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().suspended().count());
}
