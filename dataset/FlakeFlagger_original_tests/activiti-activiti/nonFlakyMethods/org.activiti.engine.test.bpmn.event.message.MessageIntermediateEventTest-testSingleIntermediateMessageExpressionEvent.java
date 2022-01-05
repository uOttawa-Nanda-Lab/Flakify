@Deployment public void testSingleIntermediateMessageExpressionEvent(){
  Map<String,Object> variableMap=new HashMap<String,Object>();
  variableMap.put("myMessageName","testMessage");
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("process",variableMap);
  List<String> activeActivityIds=runtimeService.getActiveActivityIds(pi.getId());
  assertNotNull(activeActivityIds);
  assertEquals(1,activeActivityIds.size());
  assertTrue(activeActivityIds.contains("messageCatch"));
  String messageName="testMessage";
  Execution execution=runtimeService.createExecutionQuery().messageEventSubscriptionName(messageName).singleResult();
  assertNotNull(execution);
  runtimeService.messageEventReceived(messageName,execution.getId());
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
}
