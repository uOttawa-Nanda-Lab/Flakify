@Deployment(resources={"org/activiti/engine/test/bpmn/subprocess/transaction/TransactionSubProcessTest.testSimpleCase.bpmn20.xml"}) public void testSimpleCaseTxCancelled(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transactionProcess");
  assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookHotel").count());
  assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookFlight").count());
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  assertEquals("askCustomer",task.getTaskDefinitionKey());
  taskService.setVariable(task.getId(),"confirmed",false);
  taskService.complete(task.getId());
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).activityId("afterCancellation").singleResult();
  assertNotNull(execution);
  assertEquals(0,createEventSubscriptionQuery().eventType("compensate").count());
  assertEquals(1,runtimeService.getVariable(processInstance.getId(),"undoBookHotel"));
  assertEquals(1,runtimeService.getVariable(processInstance.getId(),"undoBookFlight"));
  assertEquals(1,runtimeService.getVariable(processInstance.getId(),"undoChargeCard"));
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("undoBookFlight").count());
    assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("undoBookHotel").count());
    assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("undoChargeCard").count());
  }
  Execution receiveExecution=runtimeService.createExecutionQuery().activityId("afterCancellation").singleResult();
  runtimeService.trigger(receiveExecution.getId());
  assertProcessEnded(processInstance.getId());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
