private void testInterruptingUnderProcessDefinition(int expectedNumberOfEventSubscriptions,int numberOfExecutions){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("process");
  Execution execution=runtimeService.createExecutionQuery().messageEventSubscriptionName("newMessage").singleResult();
  assertNotNull(execution);
  assertEquals(expectedNumberOfEventSubscriptions,createEventSubscriptionQuery().count());
  assertEquals(numberOfExecutions,runtimeService.createExecutionQuery().count());
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("task",task.getTaskDefinitionKey());
  taskService.complete(task.getId());
  assertEquals(0,createEventSubscriptionQuery().count());
  assertEquals(0,runtimeService.createExecutionQuery().count());
  assertProcessEnded(processInstance.getId());
  processInstance=runtimeService.startProcessInstanceByKey("process");
  execution=runtimeService.createExecutionQuery().messageEventSubscriptionName("newMessage").singleResult();
  assertNotNull(execution);
  runtimeService.messageEventReceived("newMessage",execution.getId());
  task=taskService.createTaskQuery().singleResult();
  assertEquals("eventSubProcessTask",task.getTaskDefinitionKey());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  assertEquals(0,createEventSubscriptionQuery().count());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
