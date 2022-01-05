@Test public void testQueryLikeByQueryVariableValue(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().variableValueLike("var1","%\\%%").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance1.getId(),historicProcessInstance.getId());
    historicProcessInstance=historyService.createHistoricProcessInstanceQuery().variableValueLike("var1","%\\_%").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance2.getId(),historicProcessInstance.getId());
    historicProcessInstance=historyService.createHistoricProcessInstanceQuery().or().variableValueLike("var1","%\\%%").processDefinitionId("undefined").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance1.getId(),historicProcessInstance.getId());
    historicProcessInstance=historyService.createHistoricProcessInstanceQuery().or().variableValueLike("var1","%\\_%").processDefinitionId("undefined").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance2.getId(),historicProcessInstance.getId());
  }
}
