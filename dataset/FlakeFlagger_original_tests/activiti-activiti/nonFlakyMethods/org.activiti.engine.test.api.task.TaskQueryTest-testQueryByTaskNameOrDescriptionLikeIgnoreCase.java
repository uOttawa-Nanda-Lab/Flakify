public void testQueryByTaskNameOrDescriptionLikeIgnoreCase(){
  assertEquals(12,taskService.createTaskQuery().or().taskNameLikeIgnoreCase("%task%").taskDescriptionLikeIgnoreCase("%task%").endOr().count());
  assertEquals(9,taskService.createTaskQuery().or().taskNameLikeIgnoreCase("ACCOUN%").taskDescriptionLikeIgnoreCase("%ESCR%").endOr().count());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertEquals(12,historyService.createHistoricTaskInstanceQuery().or().taskNameLikeIgnoreCase("%task%").taskDescriptionLikeIgnoreCase("%task%").endOr().count());
    assertEquals(9,historyService.createHistoricTaskInstanceQuery().or().taskNameLikeIgnoreCase("ACCOUN%").taskDescriptionLikeIgnoreCase("%ESCR%").endOr().count());
  }
}
