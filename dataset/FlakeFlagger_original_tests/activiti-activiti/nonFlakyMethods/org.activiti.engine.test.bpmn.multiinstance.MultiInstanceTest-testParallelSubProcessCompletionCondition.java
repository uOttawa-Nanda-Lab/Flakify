@Deployment public void testParallelSubProcessCompletionCondition(){
  String procId=runtimeService.startProcessInstanceByKey("miParallelSubprocessCompletionCondition").getId();
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(4,tasks.size());
  List<Task> subProcessTasks1=taskService.createTaskQuery().taskDefinitionKey("subProcessTask1").list();
  assertEquals(2,subProcessTasks1.size());
  List<Task> subProcessTasks2=taskService.createTaskQuery().taskDefinitionKey("subProcessTask2").list();
  assertEquals(2,subProcessTasks2.size());
  Execution taskExecution=runtimeService.createExecutionQuery().executionId(subProcessTasks1.get(0).getExecutionId()).singleResult();
  String parentExecutionId=taskExecution.getParentId();
  Task subProcessTask2=null;
  for (  Task task : subProcessTasks2) {
    Execution toFindExecution=runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
    if (toFindExecution.getParentId().equals(parentExecutionId)) {
      subProcessTask2=task;
      break;
    }
  }
  assertNotNull(subProcessTask2);
  taskService.complete(tasks.get(0).getId());
  taskService.complete(subProcessTask2.getId());
  assertProcessEnded(procId);
}
