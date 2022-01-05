@Test public void testQueryByProcessInstanceNameLike(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().processInstanceNameLike("%\\%%").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance1.getId(),historicProcessInstance.getId());
    historicProcessInstance=historyService.createHistoricProcessInstanceQuery().processInstanceNameLike("%\\_%").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance2.getId(),historicProcessInstance.getId());
    historicProcessInstance=historyService.createHistoricProcessInstanceQuery().or().processInstanceNameLike("%\\%%").processDefinitionId("undefined").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance1.getId(),historicProcessInstance.getId());
    historicProcessInstance=historyService.createHistoricProcessInstanceQuery().or().processInstanceNameLike("%\\_%").processDefinitionId("undefined").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance2.getId(),historicProcessInstance.getId());
  }
}
