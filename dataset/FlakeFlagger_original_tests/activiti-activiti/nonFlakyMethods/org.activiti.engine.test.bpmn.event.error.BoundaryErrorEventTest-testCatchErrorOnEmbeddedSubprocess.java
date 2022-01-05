@Deployment public void testCatchErrorOnEmbeddedSubprocess(){
  runtimeService.startProcessInstanceByKey("boundaryErrorOnEmbeddedSubprocess");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("subprocessTask",task.getName());
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().singleResult();
  assertEquals("task after catching the error",task.getName());
}
