public void testIncludeTasks(){
  ProcessInstanceHistoryLog log=historyService.createProcessInstanceHistoryLogQuery(processInstanceId).includeTasks().singleResult();
  List<HistoricData> events=log.getHistoricData();
  assertEquals(2,events.size());
  for (  HistoricData event : events) {
    assertTrue(event instanceof HistoricTaskInstance);
  }
}
