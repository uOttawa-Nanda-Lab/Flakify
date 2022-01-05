@Deployment public void testCompensateSubprocessWithUserTask(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("compensateProcess");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("Manually undo book hotel",task.getName());
  taskService.complete(task.getId());
  Execution execution=runtimeService.createExecutionQuery().activityId("beforeEnd").singleResult();
  runtimeService.trigger(execution.getId());
  assertProcessEnded(processInstance.getId());
}
