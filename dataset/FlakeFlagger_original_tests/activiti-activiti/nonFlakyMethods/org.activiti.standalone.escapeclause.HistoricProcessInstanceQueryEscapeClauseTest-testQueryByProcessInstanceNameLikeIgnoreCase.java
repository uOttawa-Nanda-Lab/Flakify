@Test public void testQueryByProcessInstanceNameLikeIgnoreCase(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().processInstanceNameLikeIgnoreCase("%\\%%").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance1.getId(),historicProcessInstance.getId());
    historicProcessInstance=historyService.createHistoricProcessInstanceQuery().processInstanceNameLikeIgnoreCase("%\\_%").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance2.getId(),historicProcessInstance.getId());
    historicProcessInstance=historyService.createHistoricProcessInstanceQuery().or().processInstanceNameLikeIgnoreCase("%\\%%").processDefinitionId("undefined").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance1.getId(),historicProcessInstance.getId());
    historicProcessInstance=historyService.createHistoricProcessInstanceQuery().or().processInstanceNameLikeIgnoreCase("%\\_%").processDefinitionId("undefined").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance2.getId(),historicProcessInstance.getId());
  }
}
