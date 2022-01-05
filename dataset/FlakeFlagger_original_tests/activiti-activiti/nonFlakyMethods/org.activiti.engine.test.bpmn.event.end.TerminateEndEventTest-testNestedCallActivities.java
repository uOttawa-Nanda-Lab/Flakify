@Deployment public void testNestedCallActivities(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("TestNestedCallActivities");
  List<Task> tasks=assertTaskNames(processInstance,Arrays.asList("B","B","B","B","Before A","Before A","Before A","Before A","Before B","Before C"));
  taskService.complete(tasks.get(9).getId());
  tasks=assertTaskNames(processInstance,Arrays.asList("After C","B","B","B","B","Before A","Before A","Before A","Before A","Before B"));
  Task task=taskService.createTaskQuery().taskName("task_subprocess_1").singleResult();
  assertNull(task);
  taskService.complete(tasks.get(5).getId());
  for (int i=0; i < 6; i++) {
    task=taskService.createTaskQuery().taskName("subprocess1_task").singleResult();
    assertNotNull("Task is null for index " + i,task);
    taskService.complete(task.getId());
  }
  tasks=assertTaskNames(processInstance,Arrays.asList("After A","After C","B","B","B","B","Before A","Before A","Before A","Before B"));
}
