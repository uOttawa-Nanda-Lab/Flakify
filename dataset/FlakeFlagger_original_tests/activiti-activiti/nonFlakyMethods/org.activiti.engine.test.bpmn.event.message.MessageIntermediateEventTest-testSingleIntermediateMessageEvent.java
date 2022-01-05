@Deployment public void testSingleIntermediateMessageEvent(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("process");
  List<String> activeActivityIds=runtimeService.getActiveActivityIds(pi.getId());
  assertNotNull(activeActivityIds);
  assertEquals(1,activeActivityIds.size());
  assertTrue(activeActivityIds.contains("messageCatch"));
  String messageName="newInvoiceMessage";
  Execution execution=runtimeService.createExecutionQuery().messageEventSubscriptionName(messageName).singleResult();
  assertNotNull(execution);
  runtimeService.messageEventReceived(messageName,execution.getId());
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
}
