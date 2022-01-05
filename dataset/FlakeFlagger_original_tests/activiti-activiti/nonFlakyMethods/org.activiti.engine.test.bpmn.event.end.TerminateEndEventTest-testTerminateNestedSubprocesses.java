@Deployment public void testTerminateNestedSubprocesses(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("TestTerminateNestedSubprocesses");
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();
  assertEquals("A",tasks.get(0).getName());
  assertEquals("B",tasks.get(1).getName());
  assertEquals("D",tasks.get(2).getName());
  assertEquals("E",tasks.get(3).getName());
  assertEquals("F",tasks.get(4).getName());
  taskService.complete(tasks.get(3).getId());
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("H").singleResult();
  assertNotNull(task);
  taskService.complete(tasks.get(0).getId());
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("C").singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("I").singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("G").singleResult();
  assertNull(task);
  taskService.complete(tasks.get(1).getId());
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("G").singleResult();
  assertNotNull(task);
}
