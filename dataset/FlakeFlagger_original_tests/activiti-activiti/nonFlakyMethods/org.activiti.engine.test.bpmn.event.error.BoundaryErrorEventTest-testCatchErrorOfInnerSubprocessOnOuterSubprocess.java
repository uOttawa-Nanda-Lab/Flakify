@Deployment public void testCatchErrorOfInnerSubprocessOnOuterSubprocess(){
  runtimeService.startProcessInstanceByKey("boundaryErrorTest");
  List<Task> tasks=taskService.createTaskQuery().orderByTaskName().asc().list();
  assertEquals(2,tasks.size());
  assertEquals("Inner subprocess task 1",tasks.get(0).getName());
  assertEquals("Inner subprocess task 2",tasks.get(1).getName());
  taskService.complete(tasks.get(1).getId());
  taskService.createTaskQuery().list();
  Task taskAfterError=taskService.createTaskQuery().singleResult();
  assertEquals("task outside subprocess",taskAfterError.getName());
}
