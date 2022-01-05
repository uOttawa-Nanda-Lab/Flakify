@Deployment(resources={"org/activiti/engine/test/bpmn/event/compensate/CompensateEventTest.testCompensationStepEndRecorded.bpmn20.xml"}) public void testCompensationStepEndTimeRecorded(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("compensationStepEndRecordedProcess");
  assertProcessEnded(processInstance.getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    final HistoricActivityInstanceQuery query=historyService.createHistoricActivityInstanceQuery().activityId("compensationScriptTask");
    assertEquals(1,query.count());
    final HistoricActivityInstance compensationScriptTask=query.singleResult();
    assertNotNull(compensationScriptTask);
    assertNotNull(compensationScriptTask.getEndTime());
    assertNotNull(compensationScriptTask.getDurationInMillis());
  }
}
