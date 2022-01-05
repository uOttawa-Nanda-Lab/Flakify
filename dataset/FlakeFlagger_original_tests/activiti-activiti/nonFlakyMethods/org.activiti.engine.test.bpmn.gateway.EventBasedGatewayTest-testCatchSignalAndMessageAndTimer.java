@Deployment public void testCatchSignalAndMessageAndTimer(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("catchSignal");
  assertEquals(2,createEventSubscriptionQuery().count());
  EventSubscriptionQueryImpl messageEventSubscriptionQuery=createEventSubscriptionQuery().eventType("message");
  assertEquals(1,messageEventSubscriptionQuery.count());
  assertEquals(1,createEventSubscriptionQuery().eventType("signal").count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  assertEquals(1,managementService.createTimerJobQuery().count());
  Execution execution=runtimeService.createExecutionQuery().messageEventSubscriptionName("newInvoice").singleResult();
  assertNotNull(execution);
  execution=runtimeService.createExecutionQuery().signalEventSubscriptionName("alert").singleResult();
  assertNotNull(execution);
  processEngineConfiguration.getClock().setCurrentTime(new Date(processEngineConfiguration.getClock().getCurrentTime().getTime() + 10000));
  EventSubscriptionEntity messageEventSubscription=messageEventSubscriptionQuery.singleResult();
  runtimeService.messageEventReceived(messageEventSubscription.getEventName(),messageEventSubscription.getExecutionId());
  assertEquals(0,createEventSubscriptionQuery().count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  assertEquals(0,managementService.createTimerJobQuery().count());
  assertEquals(0,managementService.createJobQuery().count());
  Task task=taskService.createTaskQuery().taskName("afterMessage").singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertHistoricActivitiesDeleteReason(processInstance,DeleteReason.EVENT_BASED_GATEWAY_CANCEL,"signalEvent");
  assertHistoricActivitiesDeleteReason(processInstance,DeleteReason.EVENT_BASED_GATEWAY_CANCEL,"timerEvent");
}
