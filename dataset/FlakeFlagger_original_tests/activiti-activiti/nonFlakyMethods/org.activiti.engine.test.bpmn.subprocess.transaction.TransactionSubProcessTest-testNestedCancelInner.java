@Deployment public void testNestedCancelInner(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transactionProcess");
  assertEquals(0,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookFlight").count());
  assertEquals(5,createEventSubscriptionQuery().eventType("compensate").activityId("innerTxundoBookHotel").count());
  assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("innerTxundoBookFlight").count());
  Task taskInner=taskService.createTaskQuery().taskDefinitionKey("innerTxaskCustomer").singleResult();
  Task taskOuter=taskService.createTaskQuery().taskDefinitionKey("bookFlight").singleResult();
  assertNotNull(taskInner);
  assertNotNull(taskOuter);
  taskService.setVariable(taskInner.getId(),"confirmed",false);
  taskService.complete(taskInner.getId());
  List<String> activeActivityIds=runtimeService.getActiveActivityIds(processInstance.getId());
  assertTrue(activeActivityIds.contains("afterInnerCancellation"));
  assertEquals(0,createEventSubscriptionQuery().eventType("compensate").activityId("innerTxundoBookHotel").count());
  assertEquals(0,createEventSubscriptionQuery().eventType("compensate").activityId("innerTxundoBookFlight").count());
  assertEquals(0,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookFlight").count());
  assertEquals(5,runtimeService.getVariable(processInstance.getId(),"innerTxundoBookHotel"));
  assertEquals(1,runtimeService.getVariable(processInstance.getId(),"innerTxundoBookFlight"));
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    assertEquals(5,historyService.createHistoricActivityInstanceQuery().activityId("innerTxundoBookHotel").count());
    assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("innerTxundoBookFlight").count());
  }
  taskService.complete(taskOuter.getId());
  runtimeService.trigger(runtimeService.createExecutionQuery().activityId("afterInnerCancellation").singleResult().getId());
  assertProcessEnded(processInstance.getId());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
