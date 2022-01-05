public void testQueryMultipleAndAndSingleOr(){
  try {
    Task taskUser1Group1=taskService.newTask();
    taskUser1Group1.setAssignee("kermit");
    taskUser1Group1.setOwner("user1");
    taskUser1Group1.setPriority(10);
    taskService.saveTask(taskUser1Group1);
    taskService.addGroupIdentityLink(taskUser1Group1.getId(),"group1",IdentityLinkType.PARTICIPANT);
    Task taskUser1Group2=taskService.newTask();
    taskUser1Group2.setAssignee("kermit");
    taskUser1Group2.setOwner("user1");
    taskUser1Group2.setPriority(10);
    taskService.saveTask(taskUser1Group2);
    taskService.addGroupIdentityLink(taskUser1Group2.getId(),"group2",IdentityLinkType.PARTICIPANT);
    Task taskUser1Group1and2=taskService.newTask();
    taskUser1Group1and2.setAssignee("kermit");
    taskUser1Group1and2.setOwner("user1");
    taskUser1Group1and2.setPriority(10);
    taskService.saveTask(taskUser1Group1and2);
    taskService.addGroupIdentityLink(taskUser1Group2.getId(),"group1",IdentityLinkType.PARTICIPANT);
    taskService.addGroupIdentityLink(taskUser1Group2.getId(),"group2",IdentityLinkType.PARTICIPANT);
    Task taskUser1Group1and3=taskService.newTask();
    taskUser1Group1and3.setAssignee("kermit");
    taskUser1Group1and3.setOwner("user1");
    taskUser1Group1and3.setPriority(10);
    taskService.saveTask(taskUser1Group1and3);
    taskService.addGroupIdentityLink(taskUser1Group1and3.getId(),"group1",IdentityLinkType.PARTICIPANT);
    taskService.addGroupIdentityLink(taskUser1Group1and3.getId(),"group3",IdentityLinkType.PARTICIPANT);
    Task taskUser1Group1and4=taskService.newTask();
    taskUser1Group1and4.setAssignee("kermit");
    taskUser1Group1and4.setOwner("user1");
    taskUser1Group1and4.setPriority(10);
    taskService.saveTask(taskUser1Group1and4);
    taskService.addGroupIdentityLink(taskUser1Group1and4.getId(),"group1",IdentityLinkType.PARTICIPANT);
    taskService.addGroupIdentityLink(taskUser1Group1and4.getId(),"group4",IdentityLinkType.PARTICIPANT);
    List<String> andGroup=new ArrayList<String>();
    andGroup.add("group1");
    List<String> orGroup=new ArrayList<String>();
    orGroup.add("group2");
    orGroup.add("group4");
    assertEquals(2,taskService.createTaskQuery().taskInvolvedUser("user1").taskInvolvedGroupsIn(andGroup).or().taskInvolvedGroupsIn(orGroup).endOr().count());
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
