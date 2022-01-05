@Deployment public void testTerminateInSubProcessConcurrentMultiInstance2() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preNormalEnd").singleResult();
  taskService.complete(task.getId());
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).taskName("User Task").list();
  assertEquals(3,tasks.size());
  for (  Task t : tasks) {
    taskService.complete(t.getId());
  }
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
}
