@Deployment public void testOrQueryMultipleVariableValues(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Map<String,Object> startMap=new HashMap<String,Object>();
    startMap.put("processVar",true);
    startMap.put("anotherProcessVar",123);
    runtimeService.startProcessInstanceByKey("oneTaskProcess",startMap);
    startMap.put("anotherProcessVar",999);
    runtimeService.startProcessInstanceByKey("oneTaskProcess",startMap);
    HistoricTaskInstanceQuery query0=historyService.createHistoricTaskInstanceQuery().includeProcessVariables().or();
    for (int i=0; i < 20; i++) {
      query0=query0.processVariableValueEquals("anotherProcessVar",i);
    }
    query0=query0.endOr();
    assertNull(query0.singleResult());
    HistoricTaskInstanceQuery query1=historyService.createHistoricTaskInstanceQuery().includeProcessVariables().or().processVariableValueEquals("anotherProcessVar",123);
    for (int i=0; i < 20; i++) {
      query1=query1.processVariableValueEquals("anotherProcessVar",i);
    }
    query1=query1.endOr();
    HistoricTaskInstance task=query1.singleResult();
    assertEquals(2,task.getProcessVariables().size());
    assertEquals(true,task.getProcessVariables().get("processVar"));
    assertEquals(123,task.getProcessVariables().get("anotherProcessVar"));
  }
}
