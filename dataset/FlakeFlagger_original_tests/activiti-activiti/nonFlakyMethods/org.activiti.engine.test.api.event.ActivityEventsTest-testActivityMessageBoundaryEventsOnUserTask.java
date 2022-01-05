/** 
 * Test events related to message events, called from the API.
 */
@Deployment public void testActivityMessageBoundaryEventsOnUserTask() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("messageOnUserTaskProcess");
  assertNotNull(processInstance);
  Execution executionWithMessage=runtimeService.createExecutionQuery().messageEventSubscriptionName("message_1").singleResult();
  assertNotNull(executionWithMessage);
  runtimeService.messageEventReceived("message_1",executionWithMessage.getId());
  assertEquals(3,listener.getEventsReceived().size());
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
  assertEquals("cloudformtask1",signalEvent.getActivityId());
  assertEquals(executionWithMessage.getId(),signalEvent.getExecutionId());
  assertEquals(executionWithMessage.getProcessInstanceId(),signalEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),signalEvent.getProcessDefinitionId());
  assertNotNull(signalEvent.getCause());
  assertTrue(signalEvent.getCause() instanceof MessageEventSubscriptionEntity);
  MessageEventSubscriptionEntity cause=(MessageEventSubscriptionEntity)signalEvent.getCause();
  assertEquals("message_1",cause.getEventName());
  assertDatabaseEventPresent(ActivitiEventType.ACTIVITY_MESSAGE_RECEIVED);
}
