@Deployment public void testOnCloseFailureExecutionListenersWithTransactionalOperation(){
  MyTransactionalOperationTransactionDependentExecutionListener.clear();
  ProcessInstance firstProcessInstance=runtimeService.startProcessInstanceByKey("transactionDependentExecutionListenerProcess");
  assertProcessEnded(firstProcessInstance.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricProcessInstance> historicProcessInstances=historyService.createHistoricProcessInstanceQuery().list();
    assertEquals(1,historicProcessInstances.size());
    assertEquals("transactionDependentExecutionListenerProcess",historicProcessInstances.get(0).getProcessDefinitionKey());
  }
  ProcessInstance secondProcessInstance=runtimeService.startProcessInstanceByKey("secondTransactionDependentExecutionListenerProcess");
  assertProcessEnded(secondProcessInstance.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricProcessInstance> historicProcessInstances=historyService.createHistoricProcessInstanceQuery().list();
    assertEquals(1,historicProcessInstances.size());
    assertEquals("secondTransactionDependentExecutionListenerProcess",historicProcessInstances.get(0).getProcessDefinitionKey());
  }
  List<MyTransactionalOperationTransactionDependentExecutionListener.CurrentActivity> currentActivities=MyTransactionalOperationTransactionDependentExecutionListener.getCurrentActivities();
  assertEquals(1,currentActivities.size());
  assertEquals("serviceTask1",currentActivities.get(0).getActivityId());
  assertEquals("Service Task 1",currentActivities.get(0).getActivityName());
}
