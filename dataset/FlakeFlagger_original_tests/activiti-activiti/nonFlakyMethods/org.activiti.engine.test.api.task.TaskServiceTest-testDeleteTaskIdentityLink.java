public void testDeleteTaskIdentityLink(){
  Task task=null;
  try {
    task=taskService.newTask();
    task.setName("test");
    taskService.saveTask(task);
    taskService.addCandidateGroup(task.getId(),"sales");
    taskService.addCandidateUser(task.getId(),"kermit");
    assertNotNull(taskService.createTaskQuery().taskCandidateGroup("sales").singleResult());
    assertNotNull(taskService.createTaskQuery().taskCandidateUser("kermit").singleResult());
    taskService.deleteGroupIdentityLink(task.getId(),"sales","candidate");
    assertNull(taskService.createTaskQuery().taskCandidateGroup("sales").singleResult());
    assertNotNull(taskService.createTaskQuery().taskCandidateUser("kermit").singleResult());
  }
  finally {
    if (task != null && task.getId() != null) {
      taskService.deleteTask(task.getId());
      if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
        historyService.deleteHistoricTaskInstance(task.getId());
      }
    }
  }
}
