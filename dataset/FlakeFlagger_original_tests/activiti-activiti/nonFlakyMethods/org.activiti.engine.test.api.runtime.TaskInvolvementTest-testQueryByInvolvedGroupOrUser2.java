public void testQueryByInvolvedGroupOrUser2(){
  try {
    Task taskUser1WithGroups=taskService.newTask();
    taskUser1WithGroups.setAssignee("kermit");
    taskUser1WithGroups.setOwner("user1");
    taskUser1WithGroups.setPriority(10);
    taskService.saveTask(taskUser1WithGroups);
    taskService.addGroupIdentityLink(taskUser1WithGroups.getId(),"group1",IdentityLinkType.PARTICIPANT);
    Task taskUser1WithGroupsCandidateUser=taskService.newTask();
    taskUser1WithGroupsCandidateUser.setAssignee("kermit");
    taskUser1WithGroupsCandidateUser.setOwner("involvedUser");
    taskUser1WithGroupsCandidateUser.setPriority(10);
    taskService.saveTask(taskUser1WithGroupsCandidateUser);
    taskService.addGroupIdentityLink(taskUser1WithGroupsCandidateUser.getId(),"group1",IdentityLinkType.PARTICIPANT);
    taskService.addCandidateUser(taskUser1WithGroupsCandidateUser.getId(),"candidateUser1");
    List<String> groups=new ArrayList<String>();
    groups.add("group1");
    assertEquals(2,taskService.createTaskQuery().or().taskInvolvedUser("user1").taskInvolvedGroupsIn(groups).endOr().count());
    assertEquals(2,taskService.createTaskQuery().or().taskCandidateUser("user1").taskInvolvedGroupsIn(groups).endOr().count());
    assertEquals(2,taskService.createTaskQuery().or().taskCandidateGroup("group2").taskInvolvedGroupsIn(groups).endOr().count());
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
