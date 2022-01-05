public void testQueryByTaskDescriptionLikeIgnoreCase(){
  assertEquals(6,taskService.createTaskQuery().taskDescriptionLikeIgnoreCase("%task%").count());
  assertEquals(6,taskService.createTaskQuery().taskDescriptionLikeIgnoreCase("%Task%").count());
  assertEquals(6,taskService.createTaskQuery().taskDescriptionLikeIgnoreCase("%TASK%").count());
  assertEquals(6,taskService.createTaskQuery().taskDescriptionLikeIgnoreCase("%TaSk%").count());
  assertEquals(0,taskService.createTaskQuery().taskDescriptionLikeIgnoreCase("task%").count());
  assertEquals(1,taskService.createTaskQuery().taskDescriptionLikeIgnoreCase("gonzo%").count());
  assertEquals(1,taskService.createTaskQuery().taskDescriptionLikeIgnoreCase("Gonzo%").count());
  assertEquals(0,taskService.createTaskQuery().taskDescriptionLikeIgnoreCase("%manage%").count());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertEquals(6,historyService.createHistoricTaskInstanceQuery().taskDescriptionLikeIgnoreCase("%task%").count());
    assertEquals(6,historyService.createHistoricTaskInstanceQuery().taskDescriptionLikeIgnoreCase("%Task%").count());
    assertEquals(6,historyService.createHistoricTaskInstanceQuery().taskDescriptionLikeIgnoreCase("%TASK%").count());
    assertEquals(6,historyService.createHistoricTaskInstanceQuery().taskDescriptionLikeIgnoreCase("%TaSk%").count());
    assertEquals(0,historyService.createHistoricTaskInstanceQuery().taskDescriptionLikeIgnoreCase("task%").count());
    assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskDescriptionLikeIgnoreCase("gonzo%").count());
    assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskDescriptionLikeIgnoreCase("Gonzo%").count());
    assertEquals(0,historyService.createHistoricTaskInstanceQuery().taskDescriptionLikeIgnoreCase("%manage%").count());
  }
}
