@Deployment public void testHistoricActivityInstanceReceive(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("receiveProcess");
  HistoricActivityInstance historicActivityInstance=historyService.createHistoricActivityInstanceQuery().activityId("receive").singleResult();
  assertEquals("receive",historicActivityInstance.getActivityId());
  assertEquals("receiveTask",historicActivityInstance.getActivityType());
  assertNull(historicActivityInstance.getEndTime());
  assertNull(historicActivityInstance.getDurationInMillis());
  assertNotNull(historicActivityInstance.getProcessDefinitionId());
  assertEquals(processInstance.getId(),historicActivityInstance.getProcessInstanceId());
  assertNotNull(historicActivityInstance.getStartTime());
  Execution execution=runtimeService.createExecutionQuery().onlyChildExecutions().processInstanceId(processInstance.getId()).singleResult();
  runtimeService.trigger(execution.getId());
  historicActivityInstance=historyService.createHistoricActivityInstanceQuery().activityId("receive").singleResult();
  assertEquals("receive",historicActivityInstance.getActivityId());
  assertEquals("receiveTask",historicActivityInstance.getActivityType());
  assertNotNull(historicActivityInstance.getEndTime());
  assertTrue(historicActivityInstance.getDurationInMillis() >= 0);
  assertNotNull(historicActivityInstance.getProcessDefinitionId());
  assertEquals(processInstance.getId(),historicActivityInstance.getProcessInstanceId());
  assertNotNull(historicActivityInstance.getStartTime());
}
