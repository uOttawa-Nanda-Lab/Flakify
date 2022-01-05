@Test public void testQueryByTenantIdLike(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().processInstanceTenantIdLike("%\\%%").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance1.getId(),historicProcessInstance.getId());
    historicProcessInstance=historyService.createHistoricProcessInstanceQuery().processInstanceTenantIdLike("%\\_%").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance2.getId(),historicProcessInstance.getId());
    historicProcessInstance=historyService.createHistoricProcessInstanceQuery().or().processInstanceTenantIdLike("%\\%%").processDefinitionId("undefined").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance1.getId(),historicProcessInstance.getId());
    historicProcessInstance=historyService.createHistoricProcessInstanceQuery().or().processInstanceTenantIdLike("%\\_%").processDefinitionId("undefined").singleResult();
    assertNotNull(historicProcessInstance);
    assertEquals(processInstance2.getId(),historicProcessInstance.getId());
  }
}
