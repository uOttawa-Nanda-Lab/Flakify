@Deployment public void testNestedForkJoin(){
  runtimeService.startProcessInstanceByKey("nestedForkJoin");
  TaskQuery query=taskService.createTaskQuery().orderByTaskName().asc();
  List<Task> tasks=query.list();
  assertEquals(1,tasks.size());
  assertEquals("Task 0",tasks.get(0).getName());
  taskService.complete(tasks.get(0).getId());
  tasks=query.list();
  assertEquals(2,tasks.size());
  assertEquals("Task A",tasks.get(0).getName());
  assertEquals("Task B",tasks.get(1).getName());
  taskService.complete(tasks.get(0).getId());
  tasks=query.list();
  assertEquals(1,tasks.size());
  assertEquals("Task B",tasks.get(0).getName());
  taskService.complete(tasks.get(0).getId());
  tasks=query.list();
  assertEquals(2,tasks.size());
  assertEquals("Task B1",tasks.get(0).getName());
  assertEquals("Task B2",tasks.get(1).getName());
  taskService.complete(tasks.get(0).getId());
  taskService.complete(tasks.get(1).getId());
  tasks=query.list();
  assertEquals(1,tasks.size());
  assertEquals("Task C",tasks.get(0).getName());
}
