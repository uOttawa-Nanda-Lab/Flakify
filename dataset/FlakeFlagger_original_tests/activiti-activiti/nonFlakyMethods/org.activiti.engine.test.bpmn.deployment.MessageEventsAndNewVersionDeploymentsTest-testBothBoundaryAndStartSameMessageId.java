public void testBothBoundaryAndStartSameMessageId(){
  String deploymentId1=deployProcessWithBothStartAndBoundarySameMessage();
  assertEquals(1,getAllEventSubscriptions().size());
  assertEventSubscriptionsCount(1);
  assertEquals(0,runtimeService.createExecutionQuery().count());
  for (int i=0; i < 9; i++) {
    runtimeService.startProcessInstanceByMessage("myMessage");
  }
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    assertEquals(9,historyService.createHistoricProcessInstanceQuery().count());
  }
  assertEquals(10,getAllEventSubscriptions().size());
  String deploymentId2=deployStartMessageTestProcess();
  runtimeService.startProcessInstanceByMessage("myStartMessage");
  assertEquals(10,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(10);
  try {
    runtimeService.startProcessInstanceByMessage("myMessage");
    fail();
  }
 catch (  Exception e) {
  }
  cleanup(deploymentId1,deploymentId2);
}
