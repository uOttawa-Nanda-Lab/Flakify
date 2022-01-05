public void testQueryWithIncludeTaskVariableAndTaskCategory(){
  List<HistoricTaskInstance> tasks=historyService.createHistoricTaskInstanceQuery().taskAssignee(GONZO).list();
  for (  HistoricTaskInstance task : tasks) {
    assertNotNull(task.getCategory());
    assertEquals("testCategory",task.getCategory());
  }
  tasks=historyService.createHistoricTaskInstanceQuery().taskAssignee(GONZO).includeTaskLocalVariables().list();
  for (  HistoricTaskInstance task : tasks) {
    assertNotNull(task.getCategory());
    assertEquals("testCategory",task.getCategory());
  }
  tasks=historyService.createHistoricTaskInstanceQuery().taskAssignee(GONZO).includeProcessVariables().list();
  for (  HistoricTaskInstance task : tasks) {
    assertNotNull(task.getCategory());
    assertEquals("testCategory",task.getCategory());
  }
}
