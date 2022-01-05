@Test public void testQueryByProcessDefinitionKeyLike(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricTaskInstance> list=historyService.createHistoricTaskInstanceQuery().processDefinitionKeyLike("%\\%%").list();
    assertEquals(0,list.size());
    list=historyService.createHistoricTaskInstanceQuery().processDefinitionKeyLike("%\\_%").list();
    assertEquals(0,list.size());
    list=historyService.createHistoricTaskInstanceQuery().or().processDefinitionKeyLike("%\\%%").processDefinitionId("undefined").list();
    assertEquals(0,list.size());
    list=historyService.createHistoricTaskInstanceQuery().or().processDefinitionKeyLike("%\\_%").processDefinitionId("undefined").list();
    assertEquals(0,list.size());
  }
}
