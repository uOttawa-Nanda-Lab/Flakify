public void testQueryByInvolvedGroup(){
  try {
    Task adhocTask=taskService.newTask();
    adhocTask.setAssignee(KERMIT);
    adhocTask.setOwner(FOZZIE);
    taskService.saveTask(adhocTask);
    taskService.addGroupIdentityLink(adhocTask.getId(),"group1",IdentityLinkType.PARTICIPANT);
    List<String> groups=new ArrayList<String>();
    groups.add("group1");
    assertEquals(3,taskService.getIdentityLinksForTask(adhocTask.getId()).size());
    assertEquals(1,taskService.createTaskQuery().taskId(adhocTask.getId()).taskInvolvedGroupsIn(groups).count());
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
