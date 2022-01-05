@Deployment public void testMultiInstanceParallelReceiveTask(){
  runtimeService.startProcessInstanceByKey("multi-instance-receive");
  List<Execution> executions=runtimeService.createExecutionQuery().activityId("theReceiveTask").list();
  assertEquals(4,executions.size());
  for (  Execution execution : executions) {
    runtimeService.trigger(execution.getId());
  }
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
