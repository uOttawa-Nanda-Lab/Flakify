public void testDeleteTaskWithDeleteReason(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=taskService.newTask();
    task.setName("test task");
    taskService.saveTask(task);
    assertNotNull(task.getId());
    taskService.deleteTask(task.getId(),"deleted for testing purposes");
    HistoricTaskInstance historicTaskInstance=historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).singleResult();
    assertNotNull(historicTaskInstance);
    assertEquals("deleted for testing purposes",historicTaskInstance.getDeleteReason());
    taskService.deleteTask(task.getId(),true);
  }
}
