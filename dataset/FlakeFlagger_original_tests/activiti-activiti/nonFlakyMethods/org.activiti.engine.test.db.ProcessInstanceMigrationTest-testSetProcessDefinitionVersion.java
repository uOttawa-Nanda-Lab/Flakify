@Deployment public void testSetProcessDefinitionVersion(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("receiveTask");
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(pi.getId()).activityId("waitState1").singleResult();
  assertNotNull(execution);
  org.activiti.engine.repository.Deployment deployment=repositoryService.createDeployment().addClasspathResource(TEST_PROCESS).deploy();
  assertEquals(2,repositoryService.createProcessDefinitionQuery().count());
  CommandExecutor commandExecutor=processEngineConfiguration.getCommandExecutor();
  commandExecutor.execute(new SetProcessDefinitionVersionCmd(pi.getId(),2));
  runtimeService.trigger(execution.getId());
  ProcessDefinition newProcessDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionVersion(2).singleResult();
  pi=runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals(newProcessDefinition.getId(),pi.getProcessDefinitionId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance historicPI=historyService.createHistoricProcessInstanceQuery().processInstanceId(pi.getId()).singleResult();
    assertEquals(newProcessDefinition.getId(),historicPI.getProcessDefinitionId());
    List<HistoricActivityInstance> historicActivities=historyService.createHistoricActivityInstanceQuery().processInstanceId(pi.getId()).unfinished().list();
    assertEquals(1,historicActivities.size());
    assertEquals(newProcessDefinition.getId(),historicActivities.get(0).getProcessDefinitionId());
  }
  repositoryService.deleteDeployment(deployment.getId(),true);
}
