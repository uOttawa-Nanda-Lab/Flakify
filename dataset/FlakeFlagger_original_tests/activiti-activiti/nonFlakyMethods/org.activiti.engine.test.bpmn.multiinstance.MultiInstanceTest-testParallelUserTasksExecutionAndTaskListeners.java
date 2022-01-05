@Deployment public void testParallelUserTasksExecutionAndTaskListeners(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("miParallelUserTasks");
  List<Task> tasks=taskService.createTaskQuery().list();
  for (  Task task : tasks) {
    taskService.complete(task.getId());
  }
  Execution waitState=runtimeService.createExecutionQuery().activityId("waitState").singleResult();
  assertNotNull(waitState);
  assertEquals(3,runtimeService.getVariable(processInstance.getId(),"taskListenerCounter"));
  assertEquals(3,runtimeService.getVariable(processInstance.getId(),"executionListenerCounter"));
  runtimeService.trigger(waitState.getId());
  assertProcessEnded(processInstance.getId());
}
