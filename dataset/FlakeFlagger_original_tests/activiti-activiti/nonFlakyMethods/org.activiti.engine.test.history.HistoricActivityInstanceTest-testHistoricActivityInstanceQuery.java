@Deployment public void testHistoricActivityInstanceQuery(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("noopProcess");
  assertEquals(0,historyService.createHistoricActivityInstanceQuery().activityId("nonExistingActivityId").list().size());
  assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("noop").list().size());
  assertEquals(0,historyService.createHistoricActivityInstanceQuery().activityType("nonExistingActivityType").list().size());
  assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityType("serviceTask").list().size());
  assertEquals(0,historyService.createHistoricActivityInstanceQuery().activityName("nonExistingActivityName").list().size());
  assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityName("No operation").list().size());
  assertEquals(0,historyService.createHistoricActivityInstanceQuery().taskAssignee("nonExistingAssignee").list().size());
  assertEquals(0,historyService.createHistoricActivityInstanceQuery().executionId("nonExistingExecutionId").list().size());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    assertEquals(3,historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).list().size());
  }
 else {
    assertEquals(0,historyService.createHistoricActivityInstanceQuery().executionId(processInstance.getId()).list().size());
  }
  assertEquals(0,historyService.createHistoricActivityInstanceQuery().processInstanceId("nonExistingProcessInstanceId").list().size());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    assertEquals(3,historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).list().size());
  }
 else {
    assertEquals(0,historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).list().size());
  }
  assertEquals(0,historyService.createHistoricActivityInstanceQuery().processDefinitionId("nonExistingProcessDefinitionId").list().size());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    assertEquals(3,historyService.createHistoricActivityInstanceQuery().processDefinitionId(processInstance.getProcessDefinitionId()).list().size());
  }
 else {
    assertEquals(0,historyService.createHistoricActivityInstanceQuery().processDefinitionId(processInstance.getProcessDefinitionId()).list().size());
  }
  assertEquals(0,historyService.createHistoricActivityInstanceQuery().unfinished().list().size());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    assertEquals(3,historyService.createHistoricActivityInstanceQuery().finished().list().size());
  }
 else {
    assertEquals(0,historyService.createHistoricActivityInstanceQuery().finished().list().size());
  }
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricActivityInstance historicActivityInstance=historyService.createHistoricActivityInstanceQuery().list().get(0);
    assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityInstanceId(historicActivityInstance.getId()).list().size());
  }
}
