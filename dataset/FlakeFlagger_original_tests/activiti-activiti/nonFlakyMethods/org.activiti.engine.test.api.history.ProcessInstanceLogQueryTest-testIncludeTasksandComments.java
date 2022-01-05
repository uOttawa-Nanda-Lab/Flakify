public void testIncludeTasksandComments(){
  ProcessInstanceHistoryLog log=historyService.createProcessInstanceHistoryLogQuery(processInstanceId).includeTasks().includeComments().singleResult();
  List<HistoricData> events=log.getHistoricData();
  assertEquals(5,events.size());
  for (int i=0; i < 5; i++) {
    HistoricData event=events.get(i);
    if (i < 2) {
      assertTrue(event instanceof HistoricTaskInstance);
    }
 else {
      assertTrue(event instanceof Comment);
    }
  }
}
