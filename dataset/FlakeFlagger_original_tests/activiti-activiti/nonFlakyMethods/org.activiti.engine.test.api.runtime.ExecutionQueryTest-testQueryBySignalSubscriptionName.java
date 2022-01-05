@Deployment public void testQueryBySignalSubscriptionName(){
  runtimeService.startProcessInstanceByKey("catchSignal");
  Execution execution=runtimeService.createExecutionQuery().signalEventSubscriptionName("alert").singleResult();
  assertNotNull(execution);
  execution=runtimeService.createExecutionQuery().signalEventSubscriptionName("nonExisitng").singleResult();
  assertNull(execution);
  runtimeService.startProcessInstanceByKey("catchSignal");
  assertEquals(2,runtimeService.createExecutionQuery().signalEventSubscriptionName("alert").count());
}
