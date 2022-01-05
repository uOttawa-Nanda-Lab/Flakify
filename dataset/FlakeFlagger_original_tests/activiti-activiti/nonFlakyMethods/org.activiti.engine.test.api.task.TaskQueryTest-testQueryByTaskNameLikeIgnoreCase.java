public void testQueryByTaskNameLikeIgnoreCase(){
  assertEquals(12,taskService.createTaskQuery().taskNameLikeIgnoreCase("%task%").count());
  assertEquals(12,taskService.createTaskQuery().taskNameLikeIgnoreCase("%Task%").count());
  assertEquals(12,taskService.createTaskQuery().taskNameLikeIgnoreCase("%TASK%").count());
  assertEquals(12,taskService.createTaskQuery().taskNameLikeIgnoreCase("%TasK%").count());
  assertEquals(1,taskService.createTaskQuery().taskNameLikeIgnoreCase("gonzo%").count());
  assertEquals(1,taskService.createTaskQuery().taskNameLikeIgnoreCase("%Gonzo%").count());
  assertEquals(0,taskService.createTaskQuery().taskNameLikeIgnoreCase("Task%").count());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertEquals(12,historyService.createHistoricTaskInstanceQuery().taskNameLikeIgnoreCase("%task%").count());
    assertEquals(12,historyService.createHistoricTaskInstanceQuery().taskNameLikeIgnoreCase("%Task%").count());
    assertEquals(12,historyService.createHistoricTaskInstanceQuery().taskNameLikeIgnoreCase("%TASK%").count());
    assertEquals(12,historyService.createHistoricTaskInstanceQuery().taskNameLikeIgnoreCase("%TasK%").count());
    assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskNameLikeIgnoreCase("gonzo%").count());
    assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskNameLikeIgnoreCase("%Gonzo%").count());
    assertEquals(0,historyService.createHistoricTaskInstanceQuery().taskNameLikeIgnoreCase("Task%").count());
  }
}
