public void testIncludeVariableUpdates(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    HistoricVariableInstance historicVariableInstance=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).variableName("var").singleResult();
    assertEquals(historicVariableInstance.getValue(),LARGE_STRING_VALUE);
    ProcessInstanceHistoryLog log=historyService.createProcessInstanceHistoryLogQuery(processInstanceId).includeVariableUpdates().singleResult();
    List<HistoricData> events=log.getHistoricData();
    assertEquals(1,events.size());
    for (    HistoricData event : events) {
      assertTrue(event instanceof HistoricVariableUpdate);
      assertEquals(((HistoricDetailVariableInstanceUpdateEntity)event).getValue(),LARGE_STRING_VALUE);
    }
  }
}
