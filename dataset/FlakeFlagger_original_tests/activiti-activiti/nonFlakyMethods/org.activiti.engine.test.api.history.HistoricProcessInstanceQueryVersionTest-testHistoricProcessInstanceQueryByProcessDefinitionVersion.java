public void testHistoricProcessInstanceQueryByProcessDefinitionVersion(){
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().processDefinitionVersion(1).list().get(0).getProcessDefinitionVersion().intValue());
  assertEquals(2,historyService.createHistoricProcessInstanceQuery().processDefinitionVersion(2).list().get(0).getProcessDefinitionVersion().intValue());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().processDefinitionVersion(1).count());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().processDefinitionVersion(2).count());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().processDefinitionVersion(3).count());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().processDefinitionVersion(1).count());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().processDefinitionVersion(2).list().size());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().processDefinitionVersion(3).list().size());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().variableValueEquals("test",123).processDefinitionVersion(1).singleResult();
    assertEquals(1,processInstance.getProcessDefinitionVersion().intValue());
    Map<String,Object> variableMap=processInstance.getProcessVariables();
    assertEquals(123,variableMap.get("test"));
    processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().variableValueEquals("anothertest",456).processDefinitionVersion(1).singleResult();
    assertNull(processInstance);
    processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().variableValueEquals("anothertest",456).processDefinitionVersion(2).singleResult();
    assertEquals(2,processInstance.getProcessDefinitionVersion().intValue());
    variableMap=processInstance.getProcessVariables();
    assertEquals(456,variableMap.get("anothertest"));
  }
}
