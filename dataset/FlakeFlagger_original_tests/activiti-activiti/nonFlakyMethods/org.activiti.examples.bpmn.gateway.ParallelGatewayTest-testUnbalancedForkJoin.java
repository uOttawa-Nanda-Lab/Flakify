@Deployment public void testUnbalancedForkJoin(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("UnbalancedForkJoin");
  TaskQuery query=taskService.createTaskQuery().processInstanceId(pi.getId()).orderByTaskName().asc();
  List<Task> tasks=query.list();
  assertEquals(3,tasks.size());
  Task task1=tasks.get(0);
  assertEquals("Task 1",task1.getName());
  Task task2=tasks.get(1);
  assertEquals("Task 2",task2.getName());
  taskService.complete(task1.getId());
  taskService.complete(task2.getId());
  tasks=query.list();
  Task task3=tasks.get(0);
  assertEquals(2,tasks.size());
  assertEquals("Task 3",task3.getName());
  Task task4=tasks.get(1);
  assertEquals("Task 4",task4.getName());
  taskService.complete(task3.getId());
  taskService.complete(task4.getId());
  assertProcessEnded(pi.getId());
}
