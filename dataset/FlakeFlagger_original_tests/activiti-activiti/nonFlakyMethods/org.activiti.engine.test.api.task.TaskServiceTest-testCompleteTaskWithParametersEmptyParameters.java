@SuppressWarnings("unchecked") public void testCompleteTaskWithParametersEmptyParameters(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  String taskId=task.getId();
  taskService.complete(taskId,Collections.EMPTY_MAP);
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    historyService.deleteHistoricTaskInstance(taskId);
  }
  task=taskService.createTaskQuery().taskId(taskId).singleResult();
  assertNull(task);
}
