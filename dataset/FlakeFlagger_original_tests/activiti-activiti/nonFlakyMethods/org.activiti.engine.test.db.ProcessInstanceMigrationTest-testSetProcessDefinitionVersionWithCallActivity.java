@Deployment(resources={TEST_PROCESS_CALL_ACTIVITY}) public void testSetProcessDefinitionVersionWithCallActivity(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("parentProcess");
  Execution execution=runtimeService.createExecutionQuery().activityId("waitState1").processDefinitionKey("childProcess").singleResult();
  assertNotNull(execution);
  org.activiti.engine.repository.Deployment deployment=repositoryService.createDeployment().addClasspathResource(TEST_PROCESS_CALL_ACTIVITY).deploy();
  assertEquals(2,repositoryService.createProcessDefinitionQuery().processDefinitionKey("parentProcess").count());
  CommandExecutor commandExecutor=processEngineConfiguration.getCommandExecutor();
  commandExecutor.execute(new SetProcessDefinitionVersionCmd(pi.getId(),2));
  runtimeService.trigger(execution.getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).count());
  repositoryService.deleteDeployment(deployment.getId(),true);
}
