@Deployment public void testSorting(){
  runtimeService.startProcessInstanceByKey("process");
  int expectedActivityInstances;
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    expectedActivityInstances=2;
  }
 else {
    expectedActivityInstances=0;
  }
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceId().asc().list().size());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceStartTime().asc().list().size());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceEndTime().asc().list().size());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceDuration().asc().list().size());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByExecutionId().asc().list().size());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessDefinitionId().asc().list().size());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessInstanceId().asc().list().size());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceId().desc().list().size());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceStartTime().desc().list().size());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceEndTime().desc().list().size());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceDuration().desc().list().size());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByExecutionId().desc().list().size());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessDefinitionId().desc().list().size());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessInstanceId().desc().list().size());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceId().asc().count());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceStartTime().asc().count());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceEndTime().asc().count());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceDuration().asc().count());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByExecutionId().asc().count());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessDefinitionId().asc().count());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessInstanceId().asc().count());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceId().desc().count());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceStartTime().desc().count());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceEndTime().desc().count());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceDuration().desc().count());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByExecutionId().desc().count());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessDefinitionId().desc().count());
  assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessInstanceId().desc().count());
}
