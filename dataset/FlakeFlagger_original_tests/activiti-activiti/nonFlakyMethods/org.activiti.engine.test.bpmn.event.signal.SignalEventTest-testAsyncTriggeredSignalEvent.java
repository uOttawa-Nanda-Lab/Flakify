@Deployment public void testAsyncTriggeredSignalEvent(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("processWithSignalCatch");
  assertNotNull(processInstance);
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).signalEventSubscriptionName("The Signal").singleResult();
  assertNotNull(execution);
  assertEquals(1,createEventSubscriptionQuery().count());
  assertEquals(2,runtimeService.createExecutionQuery().count());
  runtimeService.signalEventReceivedAsync("The Signal",execution.getId());
  assertEquals(1,managementService.createJobQuery().messages().count());
  waitForJobExecutorToProcessAllJobs(8000L,200L);
  assertEquals(0,createEventSubscriptionQuery().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  assertEquals(0,managementService.createJobQuery().count());
}
