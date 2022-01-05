@Deployment(resources={TEST_PROCESS_NESTED_SUB_EXECUTIONS}) public void testSetProcessDefinitionVersionSubExecutionsNested(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("forkJoinNested");
  assertEquals(2,taskService.createTaskQuery().count());
  org.activiti.engine.repository.Deployment deployment=repositoryService.createDeployment().addClasspathResource(TEST_PROCESS_NESTED_SUB_EXECUTIONS).deploy();
  assertEquals(2,repositoryService.createProcessDefinitionQuery().count());
  CommandExecutor commandExecutor=processEngineConfiguration.getCommandExecutor();
  commandExecutor.execute(new SetProcessDefinitionVersionCmd(pi.getId(),2));
  ProcessDefinition newProcessDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionVersion(2).singleResult();
  List<Execution> executions=runtimeService.createExecutionQuery().processInstanceId(pi.getId()).list();
  for (  Execution execution : executions) {
    assertEquals(newProcessDefinition.getId(),((ExecutionEntity)execution).getProcessDefinitionId());
  }
  repositoryService.deleteDeployment(deployment.getId(),true);
}
