@Deployment(resources={"org/activiti/engine/test/bpmn/subprocess/transaction/TransactionSubProcessTest.testSimpleCase.bpmn20.xml"}) public void testSimpleCaseTxSuccessful(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transactionProcess");
  assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookHotel").count());
  assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookFlight").count());
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  taskService.setVariable(task.getId(),"confirmed",true);
  taskService.complete(task.getId());
  List<String> activeActivityIds=runtimeService.getActiveActivityIds(processInstance.getId());
  assertTrue(activeActivityIds.contains("afterSuccess"));
  EventSubscriptionEntity eventSubscriptionEntity=createEventSubscriptionQuery().eventType("compensate").activityId("tx").executionId(processInstance.getId()).singleResult();
  assertNotNull(eventSubscriptionEntity.getConfiguration());
  Execution eventScopeExecution=runtimeService.createExecutionQuery().executionId(eventSubscriptionEntity.getConfiguration()).singleResult();
  assertNotNull(eventScopeExecution);
  assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookHotel").executionId(eventScopeExecution.getId()).count());
  assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookFlight").executionId(eventScopeExecution.getId()).count());
  assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoChargeCard").executionId(eventScopeExecution.getId()).count());
  assertNull(runtimeService.getVariable(processInstance.getId(),"undoBookHotel"));
  assertNull(runtimeService.getVariable(processInstance.getId(),"undoBookFlight"));
  assertNull(runtimeService.getVariable(processInstance.getId(),"undoChargeCard"));
  Execution receiveExecution=runtimeService.createExecutionQuery().activityId("afterSuccess").singleResult();
  runtimeService.trigger(receiveExecution.getId());
  assertProcessEnded(processInstance.getId());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
