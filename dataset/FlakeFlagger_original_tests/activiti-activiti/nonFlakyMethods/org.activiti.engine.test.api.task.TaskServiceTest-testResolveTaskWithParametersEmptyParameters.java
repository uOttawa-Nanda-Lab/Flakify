@SuppressWarnings("unchecked") public void testResolveTaskWithParametersEmptyParameters(){
  Task task=taskService.newTask();
  task.setDelegationState(DelegationState.PENDING);
  taskService.saveTask(task);
  String taskId=task.getId();
  taskService.resolveTask(taskId,Collections.EMPTY_MAP);
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    historyService.deleteHistoricTaskInstance(taskId);
  }
  task=taskService.createTaskQuery().taskId(taskId).singleResult();
  assertEquals(DelegationState.RESOLVED,task.getDelegationState());
  taskService.deleteTask(taskId,true);
}
