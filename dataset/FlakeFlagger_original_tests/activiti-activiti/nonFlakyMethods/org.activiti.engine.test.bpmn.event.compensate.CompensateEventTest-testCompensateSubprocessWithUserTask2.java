@Deployment public void testCompensateSubprocessWithUserTask2(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("compensateProcess");
  Execution execution=runtimeService.createExecutionQuery().activityId("beforeEnd").singleResult();
  runtimeService.trigger(execution.getId());
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("Manually undo book hotel",task.getName());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
