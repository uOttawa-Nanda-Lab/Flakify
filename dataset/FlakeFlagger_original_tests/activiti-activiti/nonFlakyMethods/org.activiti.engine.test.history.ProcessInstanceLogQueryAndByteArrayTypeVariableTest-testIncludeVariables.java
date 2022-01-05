public void testIncludeVariables(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    ProcessInstanceHistoryLog log=historyService.createProcessInstanceHistoryLogQuery(processInstanceId).includeVariables().singleResult();
    List<HistoricData> events=log.getHistoricData();
    assertEquals(1,events.size());
    for (    HistoricData event : events) {
      assertTrue(event instanceof HistoricVariableInstance);
      assertEquals(((HistoricVariableInstanceEntity)event).getValue(),LARGE_STRING_VALUE);
    }
  }
}
