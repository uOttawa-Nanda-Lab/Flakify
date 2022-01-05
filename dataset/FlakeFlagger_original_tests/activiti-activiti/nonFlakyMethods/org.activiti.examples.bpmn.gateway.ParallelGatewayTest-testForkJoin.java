@Deployment public void testForkJoin(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("forkJoin");
  TaskQuery query=taskService.createTaskQuery().processInstanceId(pi.getId()).orderByTaskName().asc();
  List<Task> tasks=query.list();
  assertEquals(2,tasks.size());
  Task task1=tasks.get(0);
  assertEquals("Receive Payment",task1.getName());
  Task task2=tasks.get(1);
  assertEquals("Ship Order",task2.getName());
  taskService.complete(tasks.get(0).getId());
  taskService.complete(tasks.get(1).getId());
  tasks=query.list();
  assertEquals(1,tasks.size());
  assertEquals("Archive Order",tasks.get(0).getName());
}
