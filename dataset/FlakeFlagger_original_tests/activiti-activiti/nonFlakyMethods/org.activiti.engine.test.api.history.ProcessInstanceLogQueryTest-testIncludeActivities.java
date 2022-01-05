public void testIncludeActivities(){
  ProcessInstanceHistoryLog log=historyService.createProcessInstanceHistoryLogQuery(processInstanceId).includeActivities().singleResult();
  List<HistoricData> events=log.getHistoricData();
  assertEquals(5,events.size());
  for (  HistoricData event : events) {
    assertTrue(event instanceof HistoricActivityInstance);
  }
}
