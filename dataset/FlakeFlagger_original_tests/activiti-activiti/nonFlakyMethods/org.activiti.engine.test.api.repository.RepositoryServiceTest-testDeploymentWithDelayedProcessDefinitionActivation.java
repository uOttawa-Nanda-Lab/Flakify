public void testDeploymentWithDelayedProcessDefinitionActivation(){
  Date startTime=new Date();
  processEngineConfiguration.getClock().setCurrentTime(startTime);
  Date inThreeDays=new Date(startTime.getTime() + (3 * 24 * 60* 60* 1000));
  org.activiti.engine.repository.Deployment deployment=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml").addClasspathResource("org/activiti/engine/test/api/twoTasksProcess.bpmn20.xml").activateProcessDefinitionsOn(inThreeDays).deploy();
  assertEquals(1,repositoryService.createDeploymentQuery().count());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().count());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().suspended().count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().active().count());
  try {
    runtimeService.startProcessInstanceByKey("oneTaskProcess");
    fail();
  }
 catch (  ActivitiException e) {
    assertTextPresentIgnoreCase("suspended",e.getMessage());
  }
  Date inFourDays=new Date(startTime.getTime() + (4 * 24 * 60* 60* 1000));
  processEngineConfiguration.getClock().setCurrentTime(inFourDays);
  waitForJobExecutorToProcessAllJobs(5000L,50L);
  assertEquals(1,repositoryService.createDeploymentQuery().count());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().suspended().count());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().active().count());
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  repositoryService.deleteDeployment(deployment.getId(),true);
}
