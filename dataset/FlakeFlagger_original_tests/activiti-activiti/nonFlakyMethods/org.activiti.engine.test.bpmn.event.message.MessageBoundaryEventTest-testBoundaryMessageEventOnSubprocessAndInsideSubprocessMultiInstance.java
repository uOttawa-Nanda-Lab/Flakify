@Deployment public void testBoundaryMessageEventOnSubprocessAndInsideSubprocessMultiInstance(){
  runtimeService.startProcessInstanceByKey("process");
  assertEquals(18,runtimeService.createExecutionQuery().count());
  List<Task> userTasks=taskService.createTaskQuery().list();
  assertNotNull(userTasks);
  assertEquals(5,userTasks.size());
  List<Execution> executions=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName").list();
  assertNotNull(executions);
  assertEquals(5,executions.size());
  executions=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName2").list();
  assertNotNull(executions);
  assertEquals(1,executions.size());
  Execution outerScopeExecution=executions.get(0);
  runtimeService.messageEventReceived("messageName2",outerScopeExecution.getId());
  executions=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName").list();
  assertEquals(0,executions.size());
  Task userTask=taskService.createTaskQuery().singleResult();
  assertNotNull(userTask);
  assertEquals("taskAfterOuterMessageBoundary",userTask.getTaskDefinitionKey());
  taskService.complete(userTask.getId());
}
