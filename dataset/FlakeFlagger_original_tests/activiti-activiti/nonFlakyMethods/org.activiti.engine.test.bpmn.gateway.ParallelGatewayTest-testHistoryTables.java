@Deployment public void testHistoryTables(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("testHistoryRecords");
  List<HistoricActivityInstance> history=historyService.createHistoricActivityInstanceQuery().processInstanceId(pi.getId()).list();
  for (  HistoricActivityInstance h : history) {
    if (h.getActivityId().equals("parallelgateway2")) {
      assertNotNull(h.getEndTime());
    }
  }
}
