@Deployment public void testOnCompleteTransactionalOperation(){
  CurrentTaskTransactionDependentTaskListener.clear();
  ProcessInstance firstProcessInstance=runtimeService.startProcessInstanceByKey("transactionDependentTaskListenerProcess");
  assertProcessEnded(firstProcessInstance.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricProcessInstance> historicProcessInstances=historyService.createHistoricProcessInstanceQuery().list();
    assertEquals(1,historicProcessInstances.size());
    assertEquals("transactionDependentTaskListenerProcess",historicProcessInstances.get(0).getProcessDefinitionKey());
  }
  ProcessInstance secondProcessInstance=runtimeService.startProcessInstanceByKey("secondTransactionDependentTaskListenerProcess");
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(secondProcessInstance.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricProcessInstance> historicProcessInstances=historyService.createHistoricProcessInstanceQuery().list();
    assertEquals(1,historicProcessInstances.size());
    assertEquals("secondTransactionDependentTaskListenerProcess",historicProcessInstances.get(0).getProcessDefinitionKey());
  }
  List<MyTransactionalOperationTransactionDependentTaskListener.CurrentTask> currentTasks=MyTransactionalOperationTransactionDependentTaskListener.getCurrentTasks();
  assertEquals(1,currentTasks.size());
  assertEquals("usertask1",currentTasks.get(0).getTaskId());
  assertEquals("User Task 1",currentTasks.get(0).getTaskName());
}
