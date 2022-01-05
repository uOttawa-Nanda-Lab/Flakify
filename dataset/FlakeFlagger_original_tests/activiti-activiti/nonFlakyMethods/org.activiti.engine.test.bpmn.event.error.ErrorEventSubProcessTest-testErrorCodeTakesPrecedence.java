@Deployment public void testErrorCodeTakesPrecedence(){
  String procId=runtimeService.startProcessInstanceByKey("CatchErrorInEmbeddedSubProcess").getId();
  assertEquals("No tasks found in task list.",1,taskService.createTaskQuery().taskDefinitionKey("taskAfterErrorCatch2").count());
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Escalated Task",task.getName());
  taskService.complete(task.getId());
  assertProcessEnded(procId);
}
