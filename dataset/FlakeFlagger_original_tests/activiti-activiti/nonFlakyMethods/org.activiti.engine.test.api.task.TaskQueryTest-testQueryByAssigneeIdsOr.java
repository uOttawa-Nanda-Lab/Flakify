public void testQueryByAssigneeIdsOr(){
  TaskQuery query=taskService.createTaskQuery().or().taskId("invalid").taskAssigneeIds(Arrays.asList(GONZO,KERMIT));
  assertEquals(1,query.count());
  assertEquals(1,query.list().size());
  assertNotNull(query.singleResult());
  query=taskService.createTaskQuery().or().taskId("invalid").taskAssigneeIds(Arrays.asList(KERMIT,"kermit2"));
  assertEquals(0,query.count());
  assertEquals(0,query.list().size());
  assertNull(query.singleResult());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertEquals(1,historyService.createHistoricTaskInstanceQuery().or().taskId("invalid").taskAssigneeIds(Arrays.asList(GONZO,KERMIT)).count());
    assertEquals(0,historyService.createHistoricTaskInstanceQuery().or().taskId("invalid").taskAssigneeIds(Arrays.asList(KERMIT,"kermit2")).count());
  }
  Task adhocTask=taskService.newTask();
  adhocTask.setName("test");
  adhocTask.setAssignee("testAssignee");
  taskService.saveTask(adhocTask);
  query=taskService.createTaskQuery().or().taskId("invalid").taskAssigneeIds(Arrays.asList(GONZO,"testAssignee"));
  assertEquals(2,query.count());
  assertEquals(2,query.list().size());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertEquals(2,historyService.createHistoricTaskInstanceQuery().or().taskId("invalid").taskAssigneeIds(Arrays.asList(GONZO,"testAssignee")).count());
  }
  taskService.deleteTask(adhocTask.getId(),true);
}
