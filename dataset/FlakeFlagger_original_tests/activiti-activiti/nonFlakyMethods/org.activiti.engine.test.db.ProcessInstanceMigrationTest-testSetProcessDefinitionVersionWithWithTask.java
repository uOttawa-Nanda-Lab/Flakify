@Deployment(resources={TEST_PROCESS_USER_TASK_V1}) public void testSetProcessDefinitionVersionWithWithTask(){
  try {
    ProcessInstance pi=runtimeService.startProcessInstanceByKey("userTask");
    assertEquals(1,taskService.createTaskQuery().processInstanceId(pi.getId()).count());
    org.activiti.engine.repository.Deployment deployment=repositoryService.createDeployment().addClasspathResource(TEST_PROCESS_USER_TASK_V2).deploy();
    assertEquals(2,repositoryService.createProcessDefinitionQuery().processDefinitionKey("userTask").count());
    ProcessDefinition newProcessDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionKey("userTask").processDefinitionVersion(2).singleResult();
    processEngineConfiguration.getCommandExecutor().execute(new SetProcessDefinitionVersionCmd(pi.getId(),2));
    Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
    assertEquals(newProcessDefinition.getId(),task.getProcessDefinitionId());
    if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
      HistoricTaskInstance historicTask=historyService.createHistoricTaskInstanceQuery().processInstanceId(pi.getId()).singleResult();
      assertEquals(newProcessDefinition.getId(),historicTask.getProcessDefinitionId());
    }
    taskService.complete(task.getId());
    assertProcessEnded(pi.getId());
    repositoryService.deleteDeployment(deployment.getId(),true);
  }
 catch (  Exception ex) {
    ex.printStackTrace();
  }
}
