@Deployment public void testCompensateWithSubprocess(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("compensateProcess");
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    HistoricActivityInstance historicActivityInstance=historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).activityId("bookHotel").singleResult();
    assertNotNull(historicActivityInstance.getEndTime());
  }
  Task afterBookHotelTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("afterBookHotel").singleResult();
  taskService.complete(afterBookHotelTask.getId());
  Task compensationTask1=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("compensateTask1").singleResult();
  assertNotNull(compensationTask1);
  Task compensationTask2=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("compensateTask2").singleResult();
  assertNotNull(compensationTask2);
  taskService.complete(compensationTask1.getId());
  taskService.complete(compensationTask2.getId());
  Task compensationTask3=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("compensateTask3").singleResult();
  assertNotNull(compensationTask3);
  taskService.complete(compensationTask3.getId());
  assertProcessEnded(processInstance.getId());
}
