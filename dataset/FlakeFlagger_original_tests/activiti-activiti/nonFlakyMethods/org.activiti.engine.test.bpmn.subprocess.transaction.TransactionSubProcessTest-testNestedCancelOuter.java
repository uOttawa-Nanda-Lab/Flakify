@Deployment public void testNestedCancelOuter(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transactionProcess");
  assertEquals(0,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookFlight").count());
  assertEquals(5,createEventSubscriptionQuery().eventType("compensate").activityId("innerTxundoBookHotel").count());
  assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("innerTxundoBookFlight").count());
  Task taskInner=taskService.createTaskQuery().taskDefinitionKey("innerTxaskCustomer").singleResult();
  Task taskOuter=taskService.createTaskQuery().taskDefinitionKey("bookFlight").singleResult();
  assertNotNull(taskInner);
  assertNotNull(taskOuter);
  taskService.complete(taskOuter.getId());
  List<String> activeActivityIds=runtimeService.getActiveActivityIds(processInstance.getId());
  assertTrue(activeActivityIds.contains("afterOuterCancellation"));
  assertEquals(0,createEventSubscriptionQuery().eventType("compensate").activityId("innerTxundoBookHotel").count());
  assertEquals(0,createEventSubscriptionQuery().eventType("compensate").activityId("innerTxundoBookFlight").count());
  assertEquals(0,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookFlight").count());
  assertNull(runtimeService.getVariable(processInstance.getId(),"innerTxundoBookHotel"));
  assertNull(runtimeService.getVariable(processInstance.getId(),"innerTxundoBookFlight"));
  assertEquals(1,runtimeService.getVariable(processInstance.getId(),"undoBookFlight"));
  runtimeService.trigger(runtimeService.createExecutionQuery().activityId("afterOuterCancellation").singleResult().getId());
  assertProcessEnded(processInstance.getId());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
