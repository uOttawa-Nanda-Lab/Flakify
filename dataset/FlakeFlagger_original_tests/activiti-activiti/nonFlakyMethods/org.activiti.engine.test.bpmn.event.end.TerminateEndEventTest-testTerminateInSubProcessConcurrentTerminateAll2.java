@Deployment public void testTerminateInSubProcessConcurrentTerminateAll2() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).list();
  assertEquals(2,tasks.size());
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskName("User Task").singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
}
