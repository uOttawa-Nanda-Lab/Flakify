@Deployment public void testDoubleBoundaryMessageEvent(){
  runtimeService.startProcessInstanceByKey("process");
  assertEquals(4,runtimeService.createExecutionQuery().count());
  Task userTask=taskService.createTaskQuery().singleResult();
  assertNotNull(userTask);
  Execution execution1=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName_1").singleResult();
  assertNotNull(execution1);
  Execution execution2=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName_2").singleResult();
  assertNotNull(execution2);
  assertFalse(execution1.getId().equals(execution2.getId()));
  runtimeService.messageEventReceived("messageName_1",execution1.getId());
  try {
    runtimeService.messageEventReceived("messageName_2",execution2.getId());
    fail();
  }
 catch (  Exception e) {
  }
  userTask=taskService.createTaskQuery().singleResult();
  assertNotNull(userTask);
  assertEquals("taskAfterMessage_1",userTask.getTaskDefinitionKey());
  taskService.complete(userTask.getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  runtimeService.startProcessInstanceByKey("process");
  userTask=taskService.createTaskQuery().singleResult();
  assertNotNull(userTask);
  taskService.complete(userTask.getId());
  execution1=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName_1").singleResult();
  assertNull(execution1);
  execution2=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName_2").singleResult();
  assertNull(execution2);
  userTask=taskService.createTaskQuery().singleResult();
  assertNotNull(userTask);
  assertEquals("taskAfterTask",userTask.getTaskDefinitionKey());
  taskService.complete(userTask.getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
