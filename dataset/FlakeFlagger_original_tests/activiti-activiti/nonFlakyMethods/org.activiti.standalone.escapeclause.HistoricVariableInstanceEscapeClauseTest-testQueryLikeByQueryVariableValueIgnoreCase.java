@Test public void testQueryLikeByQueryVariableValueIgnoreCase(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricVariableInstance historicVariable=historyService.createHistoricVariableInstanceQuery().variableValueLikeIgnoreCase("var%","%\\%%").singleResult();
    assertNotNull(historicVariable);
    assertEquals(processInstance1.getId(),historicVariable.getProcessInstanceId());
    historicVariable=historyService.createHistoricVariableInstanceQuery().variableValueLikeIgnoreCase("var_","%\\_%").singleResult();
    assertNotNull(historicVariable);
    assertEquals(processInstance2.getId(),historicVariable.getProcessInstanceId());
  }
}
