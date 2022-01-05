public void testOrQueryMultipleVariableValues(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstanceQuery query0=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or();
    for (int i=0; i < 20; i++) {
      query0=query0.variableValueEquals("anothertest",i);
    }
    query0=query0.processDefinitionId("undefined").endOr();
    assertNull(query0.singleResult());
    HistoricProcessInstanceQuery query1=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest",123);
    for (int i=0; i < 20; i++) {
      query1=query1.variableValueEquals("anothertest",i);
    }
    query1=query1.processDefinitionId("undefined").endOr();
    HistoricProcessInstance processInstance=query1.singleResult();
    Map<String,Object> variableMap=processInstance.getProcessVariables();
    assertEquals(1,variableMap.size());
    assertEquals(123,variableMap.get("anothertest"));
    HistoricProcessInstanceQuery query2=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or();
    for (int i=0; i < 20; i++) {
      query2=query2.variableValueEquals("anothertest",i);
    }
    query2=query2.processDefinitionId("undefined").endOr().or().processDefinitionKey(PROCESS_DEFINITION_KEY_2).processDefinitionId("undefined").endOr();
    assertNull(query2.singleResult());
    HistoricProcessInstanceQuery query3=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest",123);
    for (int i=0; i < 20; i++) {
      query3=query3.variableValueEquals("anothertest",i);
    }
    query3=query3.processDefinitionId("undefined").endOr().or().processDefinitionKey(PROCESS_DEFINITION_KEY_2).processDefinitionId("undefined").endOr();
    variableMap=query3.singleResult().getProcessVariables();
    assertEquals(1,variableMap.size());
    assertEquals(123,variableMap.get("anothertest"));
  }
}
