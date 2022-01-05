@Deployment public void testBoundaryMessageEventInsideSubprocess(){
  runtimeService.startProcessInstanceByKey("process");
  assertEquals(4,runtimeService.createExecutionQuery().count());
  Task userTask=taskService.createTaskQuery().singleResult();
  assertNotNull(userTask);
  Execution execution=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName").singleResult();
  assertNotNull(execution);
  runtimeService.messageEventReceived("messageName",execution.getId());
  userTask=taskService.createTaskQuery().singleResult();
  assertNotNull(userTask);
  assertEquals("taskAfterMessage",userTask.getTaskDefinitionKey());
  taskService.complete(userTask.getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  runtimeService.startProcessInstanceByKey("process");
  userTask=taskService.createTaskQuery().singleResult();
  assertNotNull(userTask);
  taskService.complete(userTask.getId());
  execution=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName").singleResult();
  assertNull(execution);
  userTask=taskService.createTaskQuery().singleResult();
  assertNotNull(userTask);
  assertEquals("taskAfterTask",userTask.getTaskDefinitionKey());
  taskService.complete(userTask.getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
