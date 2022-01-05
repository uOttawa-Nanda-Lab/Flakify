public void testCompleteTaskWithParametersNullParameters(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  String taskId=task.getId();
  taskService.complete(taskId,null);
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    historyService.deleteHistoricTaskInstance(taskId);
  }
  task=taskService.createTaskQuery().taskId(taskId).singleResult();
  assertNull(task);
}
