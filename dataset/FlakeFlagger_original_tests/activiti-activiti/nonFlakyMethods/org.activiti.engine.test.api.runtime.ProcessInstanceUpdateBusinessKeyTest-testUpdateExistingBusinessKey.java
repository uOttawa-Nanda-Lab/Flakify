@Deployment public void testUpdateExistingBusinessKey(){
  runtimeService.startProcessInstanceByKey("businessKeyProcess","testKey");
  ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().singleResult();
  assertEquals("testKey",processInstance.getBusinessKey());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().singleResult();
    assertEquals("testKey",historicProcessInstance.getBusinessKey());
  }
  runtimeService.updateBusinessKey(processInstance.getId(),"newKey");
  processInstance=runtimeService.createProcessInstanceQuery().singleResult();
  assertEquals("newKey",processInstance.getBusinessKey());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().singleResult();
    assertEquals("newKey",historicProcessInstance.getBusinessKey());
  }
}
