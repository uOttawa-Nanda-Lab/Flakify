@Test public void testQueryLikeIgnoreCaseByQueryVariableValue(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().variableValueLikeIgnoreCase("var1","%\\%%").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance1.getId(),historicProcessInstance.getId());
    historicProcessInstance=historyService.createHistoricProcessInstanceQuery().variableValueLikeIgnoreCase("var1","%\\_%").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance2.getId(),historicProcessInstance.getId());
    historicProcessInstance=historyService.createHistoricProcessInstanceQuery().or().variableValueLikeIgnoreCase("var1","%\\%%").processDefinitionId("undefined").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance1.getId(),historicProcessInstance.getId());
    historicProcessInstance=historyService.createHistoricProcessInstanceQuery().or().variableValueLikeIgnoreCase("var1","%\\_%").processDefinitionId("undefined").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance2.getId(),historicProcessInstance.getId());
  }
}
