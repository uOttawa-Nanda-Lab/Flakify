@Deployment public void testCompensateMiSubprocessVariableSnapshots(){
  SetVariablesDelegate.variablesMap.clear();
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("compensateProcess");
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    assertEquals(5,historyService.createHistoricActivityInstanceQuery().activityId("undoBookHotel").count());
  }
  assertProcessEnded(processInstance.getId());
}
