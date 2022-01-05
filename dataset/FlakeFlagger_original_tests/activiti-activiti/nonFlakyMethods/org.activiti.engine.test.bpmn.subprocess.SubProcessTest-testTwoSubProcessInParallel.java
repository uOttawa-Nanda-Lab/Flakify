@Deployment public void testTwoSubProcessInParallel(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("twoSubProcessInParallel");
  TaskQuery taskQuery=taskService.createTaskQuery().processInstanceId(pi.getId()).orderByTaskName().asc();
  List<Task> tasks=taskQuery.list();
  assertEquals("Task in subprocess A",tasks.get(0).getName());
  assertEquals("Task in subprocess B",tasks.get(1).getName());
  taskService.complete(tasks.get(0).getId());
  tasks=taskQuery.list();
  assertEquals("Task after subprocess A",tasks.get(0).getName());
  assertEquals("Task in subprocess B",tasks.get(1).getName());
  taskService.complete(tasks.get(1).getId());
  tasks=taskQuery.list();
  assertEquals("Task after subprocess A",tasks.get(0).getName());
  assertEquals("Task after subprocess B",tasks.get(1).getName());
  taskService.complete(tasks.get(0).getId());
  taskService.complete(tasks.get(1).getId());
  assertProcessEnded(pi.getId());
}
