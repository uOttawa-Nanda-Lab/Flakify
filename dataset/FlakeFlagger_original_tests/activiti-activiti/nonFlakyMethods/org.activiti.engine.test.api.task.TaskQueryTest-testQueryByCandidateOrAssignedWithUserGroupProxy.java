public void testQueryByCandidateOrAssignedWithUserGroupProxy(){
  Mockito.when(userGroupManager.getUserGroups(KERMIT)).thenReturn(KERMITSGROUPS);
  Mockito.when(userGroupManager.getUserGroups(GONZO)).thenReturn(GONZOSGROUPS);
  Mockito.when(userGroupManager.getUserGroups(FOZZIE)).thenReturn(FOZZIESGROUPS);
  TaskQuery query=taskService.createTaskQuery().taskCandidateOrAssigned(KERMIT);
  assertEquals(11,query.count());
  List<Task> tasks=query.list();
  assertEquals(11,tasks.size());
  ArrayList<String> candidateGroups=new ArrayList<String>();
  candidateGroups.add("management");
  candidateGroups.add("accountancy");
  candidateGroups.add("noexist");
  query=taskService.createTaskQuery().taskCandidateGroupIn(candidateGroups).taskCandidateOrAssigned(KERMIT);
  assertEquals(11,query.count());
  tasks=query.list();
  assertEquals(11,tasks.size());
  query=taskService.createTaskQuery().taskCandidateOrAssigned(FOZZIE);
  assertEquals(3,query.count());
  assertEquals(3,query.list().size());
  Task task=taskService.newTask();
  task.setName("assigneeToKermit");
  task.setDescription("testTask description");
  task.setPriority(3);
  task.setAssignee(KERMIT);
  taskService.saveTask(task);
  query=taskService.createTaskQuery().taskCandidateOrAssigned(KERMIT);
  assertEquals(12,query.count());
  tasks=query.list();
  assertEquals(12,tasks.size());
  Task assigneeToKermit=taskService.createTaskQuery().taskName("assigneeToKermit").singleResult();
  taskService.deleteTask(assigneeToKermit.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    historyService.deleteHistoricTaskInstance(assigneeToKermit.getId());
  }
}
