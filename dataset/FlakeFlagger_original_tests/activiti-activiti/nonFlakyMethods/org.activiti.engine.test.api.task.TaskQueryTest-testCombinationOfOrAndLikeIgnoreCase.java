public void testCombinationOfOrAndLikeIgnoreCase(){
  assertEquals(12,taskService.createTaskQuery().or().taskNameLikeIgnoreCase("%task%").taskDescriptionLikeIgnoreCase("%desc%").taskAssigneeLikeIgnoreCase("Gonz%").taskOwnerLike("G%").endOr().count());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertEquals(12,historyService.createHistoricTaskInstanceQuery().or().taskNameLikeIgnoreCase("%task%").taskDescriptionLikeIgnoreCase("%desc%").taskAssigneeLikeIgnoreCase("Gonz%").taskOwnerLike("G%").endOr().count());
  }
}
