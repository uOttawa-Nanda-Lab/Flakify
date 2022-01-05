public void testQueryByOwnerLikeIgnoreCase(){
  assertEquals(6,taskService.createTaskQuery().taskOwnerLikeIgnoreCase("%gonzo%").count());
  assertEquals(6,taskService.createTaskQuery().taskOwnerLikeIgnoreCase("%GONZO%").count());
  assertEquals(6,taskService.createTaskQuery().taskOwnerLikeIgnoreCase("%Gon%").count());
  assertEquals(6,taskService.createTaskQuery().taskOwnerLikeIgnoreCase("gon%").count());
  assertEquals(6,taskService.createTaskQuery().taskOwnerLikeIgnoreCase("%nzo%").count());
  assertEquals(0,taskService.createTaskQuery().taskOwnerLikeIgnoreCase("%doesnotexist%").count());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertEquals(6,historyService.createHistoricTaskInstanceQuery().taskOwnerLikeIgnoreCase("%gonzo%").count());
    assertEquals(6,historyService.createHistoricTaskInstanceQuery().taskOwnerLikeIgnoreCase("%GONZO%").count());
    assertEquals(6,historyService.createHistoricTaskInstanceQuery().taskOwnerLikeIgnoreCase("%Gon%").count());
    assertEquals(6,historyService.createHistoricTaskInstanceQuery().taskOwnerLikeIgnoreCase("gon%").count());
    assertEquals(6,historyService.createHistoricTaskInstanceQuery().taskOwnerLikeIgnoreCase("%nzo%").count());
    assertEquals(0,historyService.createHistoricTaskInstanceQuery().taskOwnerLikeIgnoreCase("%doesnotexist%").count());
  }
}
