public void testQueryByprocessDefinition(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    String deploymentId=repositoryService.createDeploymentQuery().list().get(0).getId();
    HistoricProcessInstance processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().variableValueEquals("anothertest",123).deploymentId(deploymentId).singleResult();
    Map<String,Object> variableMap=processInstance.getProcessVariables();
    assertEquals(1,variableMap.size());
    assertEquals(123,variableMap.get("anothertest"));
    assertEquals(deploymentId,processInstance.getDeploymentId());
    processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().variableValueEquals("anothertest","invalid").deploymentId(deploymentId).singleResult();
    assertNull(processInstance);
    processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().variableValueEquals("anothertest",123).processDefinitionName(PROCESS_DEFINITION_NAME_2).singleResult();
    variableMap=processInstance.getProcessVariables();
    assertEquals(1,variableMap.size());
    assertEquals(123,variableMap.get("anothertest"));
    assertEquals(PROCESS_DEFINITION_NAME_2,processInstance.getProcessDefinitionName());
    processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().variableValueEquals("test","test").processDefinitionName(PROCESS_DEFINITION_NAME_2).singleResult();
    assertNull(processInstance);
    processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().variableValueEquals("anothertest",123).processDefinitionCategory(PROCESS_DEFINITION_CATEGORY_2).singleResult();
    variableMap=processInstance.getProcessVariables();
    assertEquals(1,variableMap.size());
    assertEquals(123,variableMap.get("anothertest"));
    processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().variableValueEquals("test","test").processDefinitionCategory(PROCESS_DEFINITION_CATEGORY_2).singleResult();
    assertNull(processInstance);
  }
}
