@Deployment public void testConcurrentIntermediateMessageEvent(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("process");
  List<String> activeActivityIds=runtimeService.getActiveActivityIds(pi.getId());
  assertNotNull(activeActivityIds);
  assertEquals(2,activeActivityIds.size());
  assertTrue(activeActivityIds.contains("messageCatch1"));
  assertTrue(activeActivityIds.contains("messageCatch2"));
  String messageName="newInvoiceMessage";
  List<Execution> executions=runtimeService.createExecutionQuery().messageEventSubscriptionName(messageName).list();
  assertNotNull(executions);
  assertEquals(2,executions.size());
  runtimeService.messageEventReceived(messageName,executions.get(0).getId());
  Task task=taskService.createTaskQuery().singleResult();
  assertNull(task);
  runtimeService.messageEventReceived(messageName,executions.get(1).getId());
  task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
}
