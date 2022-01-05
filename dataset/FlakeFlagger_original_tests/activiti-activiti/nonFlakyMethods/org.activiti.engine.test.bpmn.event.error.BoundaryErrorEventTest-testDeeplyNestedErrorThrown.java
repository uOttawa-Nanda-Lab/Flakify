@Deployment public void testDeeplyNestedErrorThrown(){
  String procId=runtimeService.startProcessInstanceByKey("deeplyNestedErrorThrown").getId();
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Nested task",task.getName());
  taskService.complete(task.getId(),CollectionUtil.singletonMap("input",1));
  assertProcessEnded(procId);
  procId=runtimeService.startProcessInstanceByKey("deeplyNestedErrorThrown").getId();
  task=taskService.createTaskQuery().singleResult();
  assertEquals("Nested task",task.getName());
  taskService.complete(task.getId(),CollectionUtil.singletonMap("input",2));
  task=taskService.createTaskQuery().singleResult();
  assertEquals("task after catch",task.getName());
  taskService.complete(task.getId());
  assertProcessEnded(procId);
}
