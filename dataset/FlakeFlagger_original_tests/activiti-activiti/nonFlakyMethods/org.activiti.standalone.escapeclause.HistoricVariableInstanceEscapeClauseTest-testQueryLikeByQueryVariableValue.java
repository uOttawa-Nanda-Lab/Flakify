@Test public void testQueryLikeByQueryVariableValue(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricVariableInstance historicVariable=historyService.createHistoricVariableInstanceQuery().variableValueLike("var%","%\\%%").singleResult();
    assertNotNull(historicVariable);
    assertEquals(processInstance1.getId(),historicVariable.getProcessInstanceId());
    historicVariable=historyService.createHistoricVariableInstanceQuery().variableValueLike("var_","%\\_%").singleResult();
    assertNotNull(historicVariable);
    assertEquals(processInstance2.getId(),historicVariable.getProcessInstanceId());
  }
}
