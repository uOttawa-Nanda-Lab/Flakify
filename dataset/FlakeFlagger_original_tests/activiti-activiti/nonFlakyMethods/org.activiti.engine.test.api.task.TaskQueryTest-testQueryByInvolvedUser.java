public void testQueryByInvolvedUser(){
  try {
    Task adhocTask=taskService.newTask();
    adhocTask.setAssignee(KERMIT);
    adhocTask.setOwner(FOZZIE);
    taskService.saveTask(adhocTask);
    taskService.addUserIdentityLink(adhocTask.getId(),GONZO,"customType");
    assertEquals(3,taskService.getIdentityLinksForTask(adhocTask.getId()).size());
    assertEquals(1,taskService.createTaskQuery().taskId(adhocTask.getId()).taskInvolvedUser(GONZO).count());
    assertEquals(1,taskService.createTaskQuery().taskId(adhocTask.getId()).taskInvolvedUser(KERMIT).count());
    assertEquals(1,taskService.createTaskQuery().taskId(adhocTask.getId()).taskInvolvedUser(FOZZIE).count());
  }
  finally {
    List<Task> allTasks=taskService.createTaskQuery().list();
    for (    Task task : allTasks) {
      if (task.getExecutionId() == null) {
        taskService.deleteTask(task.getId());
        if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
          historyService.deleteHistoricTaskInstance(task.getId());
        }
      }
    }
  }
}
