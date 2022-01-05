@Deployment public void testChildExecutionsSuspendedAfterProcessInstanceSuspend(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testChildExecutionsSuspended");
  runtimeService.suspendProcessInstanceById(processInstance.getId());
  List<Execution> executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();
  for (  Execution execution : executions) {
    assertTrue(execution.isSuspended());
  }
  runtimeService.activateProcessInstanceById(processInstance.getId());
  executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();
  for (  Execution execution : executions) {
    assertFalse(execution.isSuspended());
  }
  while (taskService.createTaskQuery().count() > 0) {
    for (    Task task : taskService.createTaskQuery().list()) {
      taskService.complete(task.getId());
    }
  }
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
