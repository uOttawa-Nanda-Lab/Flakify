/** 
 * Test events related to message events, called from the API.
 */
@Deployment public void testActivityMessageBoundaryEventsOnSubProcess() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("messageOnSubProcess");
  assertNotNull(processInstance);
  Execution executionWithMessage=runtimeService.createExecutionQuery().activityId("boundaryMessageEventCatching").singleResult();
  assertNotNull(executionWithMessage);
  runtimeService.messageEventReceived("message_1",executionWithMessage.getId());
  assertEquals(4,listener.getEventsReceived().size());
  assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiMessageEvent);
  ActivitiMessageEvent messageEvent=(ActivitiMessageEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ACTIVITY_MESSAGE_WAITING,messageEvent.getType());
  assertEquals("boundaryMessageEventCatching",messageEvent.getActivityId());
  assertEquals(executionWithMessage.getId(),messageEvent.getExecutionId());
  assertEquals(executionWithMessage.getProcessInstanceId(),messageEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),messageEvent.getProcessDefinitionId());
  assertEquals("message_1",messageEvent.getMessageName());
  assertNull(messageEvent.getMessageData());
  assertTrue(listener.getEventsReceived().get(1) instanceof ActivitiMessageEvent);
  messageEvent=(ActivitiMessageEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ACTIVITY_MESSAGE_RECEIVED,messageEvent.getType());
  assertEquals("boundaryMessageEventCatching",messageEvent.getActivityId());
  assertEquals(executionWithMessage.getId(),messageEvent.getExecutionId());
  assertEquals(executionWithMessage.getProcessInstanceId(),messageEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),messageEvent.getProcessDefinitionId());
  assertEquals("message_1",messageEvent.getMessageName());
  assertNull(messageEvent.getMessageData());
  assertTrue(listener.getEventsReceived().get(2) instanceof ActivitiActivityCancelledEvent);
  ActivitiActivityCancelledEvent signalEvent=(ActivitiActivityCancelledEvent)listener.getEventsReceived().get(2);
  assertEquals(ActivitiEventType.ACTIVITY_CANCELLED,signalEvent.getType());
  assertEquals("subProcess",signalEvent.getActivityId());
  assertEquals(executionWithMessage.getProcessInstanceId(),signalEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),signalEvent.getProcessDefinitionId());
  assertNotNull(signalEvent.getCause());
  assertTrue(signalEvent.getCause() instanceof MessageEventSubscriptionEntity);
  MessageEventSubscriptionEntity cause=(MessageEventSubscriptionEntity)signalEvent.getCause();
  assertEquals("message_1",cause.getEventName());
  assertTrue(listener.getEventsReceived().get(3) instanceof ActivitiActivityCancelledEvent);
  signalEvent=(ActivitiActivityCancelledEvent)listener.getEventsReceived().get(3);
  assertEquals(ActivitiEventType.ACTIVITY_CANCELLED,signalEvent.getType());
  assertEquals("cloudformtask1",signalEvent.getActivityId());
  assertEquals(executionWithMessage.getProcessInstanceId(),signalEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),signalEvent.getProcessDefinitionId());
  assertNotNull(signalEvent.getCause());
  assertTrue(signalEvent.getCause() instanceof MessageEventSubscriptionEntity);
  cause=(MessageEventSubscriptionEntity)signalEvent.getCause();
  assertEquals("message_1",cause.getEventName());
  assertDatabaseEventPresent(ActivitiEventType.ACTIVITY_MESSAGE_RECEIVED);
}
