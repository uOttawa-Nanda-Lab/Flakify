public void testIncludeComments(){
  ProcessInstanceHistoryLog log=historyService.createProcessInstanceHistoryLogQuery(processInstanceId).includeComments().singleResult();
  List<HistoricData> events=log.getHistoricData();
  assertEquals(3,events.size());
  for (  HistoricData event : events) {
    assertTrue(event instanceof Comment);
  }
}
