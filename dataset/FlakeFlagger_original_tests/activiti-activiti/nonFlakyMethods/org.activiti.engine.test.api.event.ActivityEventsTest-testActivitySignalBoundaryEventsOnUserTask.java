@Deployment public void testActivitySignalBoundaryEventsOnUserTask() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("signalOnUserTask");
  assertNotNull(processInstance);
  Execution executionWithSignal=runtimeService.createExecutionQuery().activityId("userTask").singleResult();
  assertNotNull(executionWithSignal);
  runtimeService.signalEventReceived("signalName");
  assertEquals(2,listener.getEventsReceived().size());
  assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiSignalEventImpl);
  ActivitiSignalEventImpl signalEvent=(ActivitiSignalEventImpl)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ACTIVITY_SIGNALED,signalEvent.getType());
  assertEquals("boundarySignalEventCatching",signalEvent.getActivityId());
  assertEquals(executionWithSignal.getProcessInstanceId(),signalEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),signalEvent.getProcessDefinitionId());
  assertTrue(listener.getEventsReceived().get(1) instanceof ActivitiActivityCancelledEvent);
  ActivitiActivityCancelledEvent cancelEvent=(ActivitiActivityCancelledEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ACTIVITY_CANCELLED,cancelEvent.getType());
  assertEquals("userTask",cancelEvent.getActivityId());
  assertEquals(executionWithSignal.getProcessInstanceId(),cancelEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),cancelEvent.getProcessDefinitionId());
  assertNotNull(cancelEvent.getCause());
  assertTrue(cancelEvent.getCause() instanceof SignalEventSubscriptionEntity);
  SignalEventSubscriptionEntity cause=(SignalEventSubscriptionEntity)cancelEvent.getCause();
  assertEquals("signalName",cause.getEventName());
}
