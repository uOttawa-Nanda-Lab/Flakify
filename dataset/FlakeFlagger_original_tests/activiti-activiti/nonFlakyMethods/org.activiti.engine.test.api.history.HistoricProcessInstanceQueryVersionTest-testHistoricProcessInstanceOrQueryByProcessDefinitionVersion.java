public void testHistoricProcessInstanceOrQueryByProcessDefinitionVersion(){
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processDefinitionVersion(1).processDefinitionId("undefined").endOr().count());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processDefinitionVersion(2).processDefinitionId("undefined").endOr().count());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().processDefinitionVersion(3).processDefinitionId("undefined").endOr().count());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processDefinitionVersion(1).processDefinitionId("undefined").endOr().list().size());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processDefinitionVersion(2).processDefinitionId("undefined").endOr().list().size());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().processDefinitionVersion(3).processDefinitionId("undefined").endOr().list().size());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("test","invalid").processDefinitionVersion(1).endOr().singleResult();
    assertEquals(1,processInstance.getProcessDefinitionVersion().intValue());
    Map<String,Object> variableMap=processInstance.getProcessVariables();
    assertEquals(123,variableMap.get("test"));
    processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest","invalid").processDefinitionVersion(2).endOr().singleResult();
    assertEquals(2,processInstance.getProcessDefinitionVersion().intValue());
    variableMap=processInstance.getProcessVariables();
    assertEquals(456,variableMap.get("anothertest"));
    processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().variableValueEquals("anothertest","invalid").processDefinitionVersion(3).singleResult();
    assertNull(processInstance);
  }
}
