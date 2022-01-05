/** 
 * Test events related to message events, called from the API, targeting an event-subprocess.
 */
@Deployment public void testActivityMessageEventsInEventSubprocess() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("messageProcess");
  assertNotNull(processInstance);
  Execution executionWithMessage=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName").singleResult();
  assertNotNull(executionWithMessage);
  runtimeService.messageEventReceived("messageName",executionWithMessage.getId());
  assertEquals(2,listener.getEventsReceived().size());
  assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiMessageEvent);
  ActivitiMessageEvent messageEvent=(ActivitiMessageEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ACTIVITY_MESSAGE_WAITING,messageEvent.getType());
  assertEquals("catchMessage",messageEvent.getActivityId());
  assertEquals(executionWithMessage.getId(),messageEvent.getExecutionId());
  assertEquals(executionWithMessage.getProcessInstanceId(),messageEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),messageEvent.getProcessDefinitionId());
  assertEquals("messageName",messageEvent.getMessageName());
  assertNull(messageEvent.getMessageData());
  assertTrue(listener.getEventsReceived().get(1) instanceof ActivitiMessageEvent);
  messageEvent=(ActivitiMessageEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ACTIVITY_MESSAGE_RECEIVED,messageEvent.getType());
  assertEquals("catchMessage",messageEvent.getActivityId());
  assertEquals(executionWithMessage.getId(),messageEvent.getExecutionId());
  assertEquals(executionWithMessage.getProcessInstanceId(),messageEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),messageEvent.getProcessDefinitionId());
  assertEquals("messageName",messageEvent.getMessageName());
  assertNull(messageEvent.getMessageData());
}
