@Deployment public void testDeeplyNestedErrorThrownOnlyAutomaticSteps(){
  String procId=runtimeService.startProcessInstanceByKey("deeplyNestedErrorThrown",CollectionUtil.singletonMap("input",1)).getId();
  assertProcessEnded(procId);
  HistoricProcessInstance hip;
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    hip=historyService.createHistoricProcessInstanceQuery().processInstanceId(procId).singleResult();
    assertEquals("processEnd1",hip.getEndActivityId());
  }
  procId=runtimeService.startProcessInstanceByKey("deeplyNestedErrorThrown",CollectionUtil.singletonMap("input",1)).getId();
  assertProcessEnded(procId);
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    hip=historyService.createHistoricProcessInstanceQuery().processInstanceId(procId).singleResult();
    assertEquals("processEnd1",hip.getEndActivityId());
  }
}
