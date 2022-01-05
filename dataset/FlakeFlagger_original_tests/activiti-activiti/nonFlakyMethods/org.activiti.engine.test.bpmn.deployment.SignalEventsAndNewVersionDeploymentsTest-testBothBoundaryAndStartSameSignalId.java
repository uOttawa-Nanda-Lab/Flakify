public void testBothBoundaryAndStartSameSignalId(){
  String deploymentId1=deployProcessWithBothStartAndBoundarySignalSameSignal();
  assertEventSubscriptionsCount(1);
  assertEquals(0,runtimeService.createExecutionQuery().count());
  for (int i=0; i < 9; i++) {
    runtimeService.signalEventReceived("mySignal");
    assertEquals(2,getAllEventSubscriptions().size());
  }
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    assertEquals(9,historyService.createHistoricProcessInstanceQuery().count());
  }
  assertEquals(2,getAllEventSubscriptions().size());
  runtimeService.signalEventReceived("myStartSignal");
  String deploymentId2=deployStartSignalTestProcess();
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(2,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(2);
  cleanup(deploymentId1,deploymentId2);
}
