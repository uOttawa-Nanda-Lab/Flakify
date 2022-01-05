@Deployment public void testHistoricActivityInstanceNoop(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("noopProcess");
  HistoricActivityInstance historicActivityInstance=historyService.createHistoricActivityInstanceQuery().activityId("noop").singleResult();
  assertEquals("noop",historicActivityInstance.getActivityId());
  assertEquals("serviceTask",historicActivityInstance.getActivityType());
  assertNotNull(historicActivityInstance.getProcessDefinitionId());
  assertEquals(processInstance.getId(),historicActivityInstance.getProcessInstanceId());
  assertNotNull(historicActivityInstance.getStartTime());
  assertNotNull(historicActivityInstance.getEndTime());
  assertTrue(historicActivityInstance.getDurationInMillis() >= 0);
}
