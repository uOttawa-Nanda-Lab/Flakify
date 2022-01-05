@Test public void testQueryByProcessDefinitionKeyLikeIgnoreCase(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricTaskInstance> list=historyService.createHistoricTaskInstanceQuery().processDefinitionKeyLikeIgnoreCase("%\\%%").list();
    assertEquals(0,list.size());
    list=historyService.createHistoricTaskInstanceQuery().processDefinitionKeyLikeIgnoreCase("%\\_%").list();
    assertEquals(0,list.size());
    list=historyService.createHistoricTaskInstanceQuery().or().processDefinitionKeyLikeIgnoreCase("%\\%%").processDefinitionId("undefined").list();
    assertEquals(0,list.size());
    list=historyService.createHistoricTaskInstanceQuery().or().processDefinitionKeyLikeIgnoreCase("%\\_%").processDefinitionId("undefined").list();
    assertEquals(0,list.size());
  }
}
