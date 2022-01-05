/** 
 * Test events related to signalling
 */
@Deployment public void testActivitySignalEvents() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("signalProcess");
  assertNotNull(processInstance);
  Execution executionWithSignal=runtimeService.createExecutionQuery().activityId("receivePayment").singleResult();
  assertNotNull(executionWithSignal);
  runtimeService.trigger(executionWithSignal.getId());
  assertEquals(1,listener.getEventsReceived().size());
  assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiSignalEvent);
  ActivitiSignalEvent signalEvent=(ActivitiSignalEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ACTIVITY_SIGNALED,signalEvent.getType());
  assertEquals("receivePayment",signalEvent.getActivityId());
  assertEquals(executionWithSignal.getId(),signalEvent.getExecutionId());
  assertEquals(executionWithSignal.getProcessInstanceId(),signalEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),signalEvent.getProcessDefinitionId());
  assertNull(signalEvent.getSignalName());
  assertNull(signalEvent.getSignalData());
  listener.clearEventsReceived();
  Execution executionWithSignalEvent=runtimeService.createExecutionQuery().activityId("shipOrder").singleResult();
  runtimeService.signalEventReceived("alert",executionWithSignalEvent.getId(),Collections.singletonMap("test",(Object)"test"));
  assertEquals(1,listener.getEventsReceived().size());
  assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiSignalEvent);
  signalEvent=(ActivitiSignalEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ACTIVITY_SIGNALED,signalEvent.getType());
  assertEquals("shipOrder",signalEvent.getActivityId());
  assertEquals(executionWithSignalEvent.getId(),signalEvent.getExecutionId());
  assertEquals(executionWithSignalEvent.getProcessInstanceId(),signalEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),signalEvent.getProcessDefinitionId());
  assertEquals("alert",signalEvent.getSignalName());
  assertNotNull(signalEvent.getSignalData());
  listener.clearEventsReceived();
  assertDatabaseEventPresent(ActivitiEventType.ACTIVITY_SIGNALED);
}
