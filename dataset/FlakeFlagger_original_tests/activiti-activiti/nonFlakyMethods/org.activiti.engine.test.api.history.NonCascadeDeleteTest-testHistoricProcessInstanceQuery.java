@Test public void testHistoricProcessInstanceQuery(){
  deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml").deploy().getId();
  processInstanceId=runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY).getId();
  Task task=taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
  taskService.complete(task.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance processInstance=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    assertEquals(PROCESS_DEFINITION_KEY,processInstance.getProcessDefinitionKey());
    repositoryService.deleteDeployment(deploymentId,false);
    HistoricProcessInstance processInstanceAfterDelete=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    assertNull(processInstanceAfterDelete.getProcessDefinitionKey());
    assertNull(processInstanceAfterDelete.getProcessDefinitionName());
    assertNull(processInstanceAfterDelete.getProcessDefinitionVersion());
    historyService.deleteHistoricProcessInstance(processInstanceId);
  }
}
