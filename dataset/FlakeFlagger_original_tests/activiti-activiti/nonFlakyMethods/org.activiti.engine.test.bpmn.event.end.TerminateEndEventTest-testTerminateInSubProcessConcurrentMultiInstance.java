@Deployment public void testTerminateInSubProcessConcurrentMultiInstance() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).list();
  assertEquals(4,tasks.size());
  long executionEntitiesCount=runtimeService.createExecutionQuery().count();
  assertEquals(9,executionEntitiesCount);
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preNormalEnd").singleResult();
  taskService.complete(task.getId());
  executionEntitiesCount=runtimeService.createExecutionQuery().count();
  assertEquals(8,executionEntitiesCount);
  tasks=taskService.createTaskQuery().list();
  for (  Task t : tasks) {
    taskService.complete(t.getId());
  }
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
}
