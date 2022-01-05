@Deployment(resources={TEST_PROCESS}) public void testSetProcessDefinitionVersionActivityMissing(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("receiveTask");
  Execution execution=runtimeService.createExecutionQuery().activityId("waitState1").singleResult();
  assertNotNull(execution);
  org.activiti.engine.repository.Deployment deployment=repositoryService.createDeployment().addClasspathResource(TEST_PROCESS_ACTIVITY_MISSING).deploy();
  assertEquals(2,repositoryService.createProcessDefinitionQuery().count());
  CommandExecutor commandExecutor=processEngineConfiguration.getCommandExecutor();
  SetProcessDefinitionVersionCmd setProcessDefinitionVersionCmd=new SetProcessDefinitionVersionCmd(pi.getId(),2);
  try {
    commandExecutor.execute(setProcessDefinitionVersionCmd);
    fail("ActivitiException expected");
  }
 catch (  ActivitiException ae) {
    assertTextPresent("The new process definition (key = 'receiveTask') does not contain the current activity (id = 'waitState1') of the process instance (id = '",ae.getMessage());
  }
  repositoryService.deleteDeployment(deployment.getId(),true);
}
