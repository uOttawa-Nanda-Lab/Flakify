@Deployment(resources={"org/activiti/engine/test/bpmn/event/compensate/CompensateEventTest.testCallActivityCompensationHandler.bpmn20.xml","org/activiti/engine/test/bpmn/event/compensate/CompensationHandler.bpmn20.xml"}) public void testCallActivityCompensationHandler(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("compensateProcess");
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    assertEquals(5,historyService.createHistoricActivityInstanceQuery().activityId("undoBookHotel").count());
  }
  Execution execution=runtimeService.createExecutionQuery().activityId("beforeEnd").singleResult();
  runtimeService.trigger(execution.getId());
  assertProcessEnded(processInstance.getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    assertEquals(6,historyService.createHistoricProcessInstanceQuery().count());
  }
}
