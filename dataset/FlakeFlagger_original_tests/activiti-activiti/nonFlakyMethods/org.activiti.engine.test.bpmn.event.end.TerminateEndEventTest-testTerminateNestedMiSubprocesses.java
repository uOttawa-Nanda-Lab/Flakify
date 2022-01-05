@Deployment public void testTerminateNestedMiSubprocesses(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("TestTerminateNestedMiSubprocesses");
  taskService.complete(taskService.createTaskQuery().taskName("A").singleResult().getId());
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("C").list();
  assertEquals(7,tasks.size());
  for (  Task task : tasks) {
    taskService.complete(task.getId());
  }
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("I").singleResult();
  assertNotNull(task);
  tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("E").list();
  assertEquals(3,tasks.size());
  for (  Task t : tasks) {
    taskService.complete(t.getId());
  }
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("H").singleResult();
  assertNotNull(task);
}
