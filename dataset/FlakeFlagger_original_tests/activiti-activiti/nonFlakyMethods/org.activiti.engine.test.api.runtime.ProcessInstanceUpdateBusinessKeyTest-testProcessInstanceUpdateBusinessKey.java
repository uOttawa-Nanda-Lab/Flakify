@Deployment public void testProcessInstanceUpdateBusinessKey(){
  runtimeService.startProcessInstanceByKey("businessKeyProcess");
  ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().singleResult();
  assertEquals("bzKey",processInstance.getBusinessKey());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().singleResult();
    assertEquals("bzKey",historicProcessInstance.getBusinessKey());
  }
}
