public void testEverything(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    ProcessInstanceHistoryLog log=historyService.createProcessInstanceHistoryLogQuery(processInstanceId).includeTasks().includeActivities().includeComments().includeVariables().includeVariableUpdates().singleResult();
    List<HistoricData> events=log.getHistoricData();
    assertEquals(15,events.size());
  }
}
