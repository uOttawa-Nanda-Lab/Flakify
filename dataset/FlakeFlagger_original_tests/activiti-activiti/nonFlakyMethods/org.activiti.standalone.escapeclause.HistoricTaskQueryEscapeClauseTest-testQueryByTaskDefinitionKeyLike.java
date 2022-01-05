@Test public void testQueryByTaskDefinitionKeyLike(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricTaskInstance> list=historyService.createHistoricTaskInstanceQuery().taskDefinitionKeyLike("%\\%%").list();
    assertEquals(0,list.size());
    list=historyService.createHistoricTaskInstanceQuery().taskDefinitionKeyLike("%\\_%").list();
    assertEquals(0,list.size());
    list=historyService.createHistoricTaskInstanceQuery().or().taskDefinitionKeyLike("%\\%%").processDefinitionId("undefined").list();
    assertEquals(0,list.size());
    list=historyService.createHistoricTaskInstanceQuery().or().taskDefinitionKeyLike("%\\_%").processDefinitionId("undefined").list();
    assertEquals(0,list.size());
  }
}
