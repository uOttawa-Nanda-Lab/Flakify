@Deployment public void testCompleteAfterParallelGateway() throws InterruptedException {
  runtimeService.startProcessInstanceByKey("ForkProcess");
  List<Task> taskList=taskService.createTaskQuery().list();
  assertNotNull(taskList);
  assertEquals(2,taskList.size());
  Task task=taskService.createTaskQuery().taskDefinitionKey("SimpleUser").singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
}
