public void testQueryByAssigneeLikeIgnoreCase(){
  assertEquals(1,taskService.createTaskQuery().taskAssigneeLikeIgnoreCase("%gonzo%").count());
  assertEquals(1,taskService.createTaskQuery().taskAssigneeLikeIgnoreCase("%GONZO%").count());
  assertEquals(1,taskService.createTaskQuery().taskAssigneeLikeIgnoreCase("%Gon%").count());
  assertEquals(1,taskService.createTaskQuery().taskAssigneeLikeIgnoreCase("gon%").count());
  assertEquals(1,taskService.createTaskQuery().taskAssigneeLikeIgnoreCase("%nzo%").count());
  assertEquals(0,taskService.createTaskQuery().taskAssigneeLikeIgnoreCase("%doesnotexist%").count());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskAssigneeLikeIgnoreCase("%gonzo%").count());
    assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskAssigneeLikeIgnoreCase("%GONZO%").count());
    assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskAssigneeLikeIgnoreCase("%Gon%").count());
    assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskAssigneeLikeIgnoreCase("gon%").count());
    assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskAssigneeLikeIgnoreCase("%nzo%").count());
    assertEquals(0,historyService.createHistoricTaskInstanceQuery().taskAssigneeLikeIgnoreCase("%doesnotexist%").count());
  }
}
