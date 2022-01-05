@Test public void testQueryByVariableNameLike(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricVariableInstance historicVariable=historyService.createHistoricVariableInstanceQuery().variableNameLike("%\\%%").singleResult();
    assertNotNull(historicVariable);
    assertEquals(processInstance1.getId(),historicVariable.getProcessInstanceId());
    assertEquals("One%",historicVariable.getValue());
    historicVariable=historyService.createHistoricVariableInstanceQuery().variableNameLike("%\\_%").singleResult();
    assertNotNull(historicVariable);
    assertEquals(processInstance2.getId(),historicVariable.getProcessInstanceId());
    assertEquals("Two_",historicVariable.getValue());
  }
}
