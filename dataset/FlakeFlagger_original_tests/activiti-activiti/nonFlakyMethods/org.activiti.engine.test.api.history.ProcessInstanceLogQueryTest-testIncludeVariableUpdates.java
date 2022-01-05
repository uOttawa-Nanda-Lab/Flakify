public void testIncludeVariableUpdates(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    ProcessInstanceHistoryLog log=historyService.createProcessInstanceHistoryLogQuery(processInstanceId).includeVariableUpdates().singleResult();
    List<HistoricData> events=log.getHistoricData();
    assertEquals(3,events.size());
    for (    HistoricData event : events) {
      assertTrue(event instanceof HistoricVariableUpdate);
    }
  }
}
