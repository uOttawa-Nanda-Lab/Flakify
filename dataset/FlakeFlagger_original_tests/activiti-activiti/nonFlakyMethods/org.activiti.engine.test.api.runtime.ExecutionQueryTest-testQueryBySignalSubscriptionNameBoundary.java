@Deployment public void testQueryBySignalSubscriptionNameBoundary(){
  runtimeService.startProcessInstanceByKey("signalProces");
  Execution execution=runtimeService.createExecutionQuery().signalEventSubscriptionName("Test signal").singleResult();
  assertNotNull(execution);
  execution=runtimeService.createExecutionQuery().signalEventSubscriptionName("nonExisitng").singleResult();
  assertNull(execution);
  runtimeService.startProcessInstanceByKey("signalProces");
  assertEquals(2,runtimeService.createExecutionQuery().signalEventSubscriptionName("Test signal").count());
}
