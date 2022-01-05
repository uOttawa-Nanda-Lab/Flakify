@Deployment public void testCancelEndConcurrent(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transactionProcess");
  assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookHotel").count());
  assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookFlight").count());
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  assertEquals("askCustomer",task.getTaskDefinitionKey());
  taskService.setVariable(task.getId(),"confirmed",false);
  taskService.complete(task.getId());
  List<String> activeActivityIds=runtimeService.getActiveActivityIds(processInstance.getId());
  assertTrue(activeActivityIds.contains("afterCancellation"));
  assertEquals(0,createEventSubscriptionQuery().eventType("compensate").count());
  assertEquals(1,runtimeService.getVariable(processInstance.getId(),"undoBookHotel"));
  assertEquals(1,runtimeService.getVariable(processInstance.getId(),"undoBookFlight"));
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("undoBookHotel").count());
    assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("undoBookFlight").count());
  }
  Execution receiveExecution=runtimeService.createExecutionQuery().activityId("afterCancellation").singleResult();
  runtimeService.trigger(receiveExecution.getId());
  assertProcessEnded(processInstance.getId());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
