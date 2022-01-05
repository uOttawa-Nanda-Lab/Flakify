public void testOrQueryByprocessDefinition(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    String deploymentId=repositoryService.createDeploymentQuery().list().get(0).getId();
    HistoricProcessInstanceQuery historicprocessInstanceQuery=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest","invalid").deploymentId(deploymentId).endOr();
    assertEquals(6,historicprocessInstanceQuery.list().size());
    assertEquals(6,historicprocessInstanceQuery.count());
    Map<String,Object> variableMap=historicprocessInstanceQuery.list().get(4).getProcessVariables();
    assertEquals(1,variableMap.size());
    assertEquals(123,variableMap.get("anothertest"));
    for (    HistoricProcessInstance processInstance : historicprocessInstanceQuery.list()) {
      assertEquals(deploymentId,processInstance.getDeploymentId());
    }
    HistoricProcessInstance processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest","invalid").deploymentId("invalid").endOr().singleResult();
    assertNull(processInstance);
    processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest","invalid").processDefinitionName(PROCESS_DEFINITION_NAME_2).endOr().singleResult();
    variableMap=processInstance.getProcessVariables();
    assertEquals(1,variableMap.size());
    assertEquals(123,variableMap.get("anothertest"));
    assertEquals(PROCESS_DEFINITION_NAME_2,processInstance.getProcessDefinitionName());
    processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest","invalid").processDefinitionName("invalid").endOr().singleResult();
    assertNull(processInstance);
    processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest","invalid").processDefinitionCategory(PROCESS_DEFINITION_CATEGORY_2).endOr().singleResult();
    variableMap=processInstance.getProcessVariables();
    assertEquals(1,variableMap.size());
    assertEquals(123,variableMap.get("anothertest"));
    processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest","invalid").processDefinitionCategory("invalid").endOr().singleResult();
    assertNull(processInstance);
  }
}
