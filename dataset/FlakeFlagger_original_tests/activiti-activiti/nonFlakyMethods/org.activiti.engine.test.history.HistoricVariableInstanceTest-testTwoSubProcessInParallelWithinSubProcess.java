@Deployment public void testTwoSubProcessInParallelWithinSubProcess(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("twoSubProcessInParallelWithinSubProcess");
    assertProcessEnded(processInstance.getId());
    List<HistoricVariableInstance> variables=historyService.createHistoricVariableInstanceQuery().orderByVariableName().asc().list();
    assertEquals(2,variables.size());
    HistoricVariableInstanceEntity historicVariable=(HistoricVariableInstanceEntity)variables.get(0);
    assertEquals("myVar",historicVariable.getName());
    assertEquals("test101112",historicVariable.getTextValue());
    HistoricVariableInstanceEntity historicVariable1=(HistoricVariableInstanceEntity)variables.get(1);
    assertEquals("myVar1",historicVariable1.getName());
    assertEquals("test789",historicVariable1.getTextValue());
    assertEquals(18,historyService.createHistoricActivityInstanceQuery().count());
    assertEquals(7,historyService.createHistoricDetailQuery().count());
  }
}
