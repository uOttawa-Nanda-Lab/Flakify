@Test public void testQueryByProcessKeyNotIn(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<String> processDefinitionKeyNotIn1=new ArrayList<String>();
    processDefinitionKeyNotIn1.add("%\\%%");
    List<String> processDefinitionKeyNotIn2=new ArrayList<String>();
    processDefinitionKeyNotIn2.add("%\\_%");
    List<String> processDefinitionKeyNotIn3=new ArrayList<String>();
    processDefinitionKeyNotIn3.add("%");
    List<String> processDefinitionKeyNotIn4=new ArrayList<String>();
    processDefinitionKeyNotIn4.add("______________");
    HistoricProcessInstanceQuery query=historyService.createHistoricProcessInstanceQuery().processDefinitionKeyNotIn(processDefinitionKeyNotIn1);
    assertEquals(2,query.list().size());
    assertEquals(2,query.list().size());
    query=historyService.createHistoricProcessInstanceQuery().processDefinitionKeyNotIn(processDefinitionKeyNotIn2);
    assertEquals(2,query.list().size());
    assertEquals(2,query.list().size());
    query=historyService.createHistoricProcessInstanceQuery().processDefinitionKeyNotIn(processDefinitionKeyNotIn3);
    assertEquals(0,query.list().size());
    assertEquals(0,query.list().size());
    query=historyService.createHistoricProcessInstanceQuery().processDefinitionKeyNotIn(processDefinitionKeyNotIn4);
    assertEquals(0,query.list().size());
    assertEquals(0,query.list().size());
    query=historyService.createHistoricProcessInstanceQuery().or().processDefinitionKeyNotIn(processDefinitionKeyNotIn1).processDefinitionId("undefined");
    assertEquals(2,query.list().size());
    assertEquals(2,query.list().size());
    query=historyService.createHistoricProcessInstanceQuery().or().processDefinitionKeyNotIn(processDefinitionKeyNotIn2).processDefinitionId("undefined");
    assertEquals(2,query.list().size());
    assertEquals(2,query.list().size());
    query=historyService.createHistoricProcessInstanceQuery().or().processDefinitionKeyNotIn(processDefinitionKeyNotIn3).processDefinitionId("undefined");
    assertEquals(0,query.list().size());
    assertEquals(0,query.list().size());
    query=historyService.createHistoricProcessInstanceQuery().or().processDefinitionKeyNotIn(processDefinitionKeyNotIn4).processDefinitionId("undefined");
    assertEquals(0,query.list().size());
    assertEquals(0,query.list().size());
  }
}
