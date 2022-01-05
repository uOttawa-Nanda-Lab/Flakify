@Deployment public void testQueryByExecutionId(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("catchSignal");
  runtimeService.startProcessInstanceByKey("catchSignal");
  EventSubscriptionEntity subscription=newEventSubscriptionQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(subscription);
  Execution executionWaitingForSignal=runtimeService.createExecutionQuery().activityId("signalEvent").processInstanceId(processInstance.getId()).singleResult();
  EventSubscriptionEntity signalSubscription=newEventSubscriptionQuery().executionId(executionWaitingForSignal.getId()).singleResult();
  assertNotNull(signalSubscription);
  assertEquals(signalSubscription,subscription);
  cleanDb();
}
