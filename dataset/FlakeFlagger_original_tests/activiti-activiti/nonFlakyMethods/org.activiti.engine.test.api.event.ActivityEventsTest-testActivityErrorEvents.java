/** 
 * Test events related to error-events
 */
@Deployment public void testActivityErrorEvents() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("errorProcess");
  assertNotNull(processInstance);
  ProcessInstance afterErrorInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNull(afterErrorInstance);
  ActivitiErrorEvent errorEvent=null;
  for (  ActivitiEvent event : listener.getEventsReceived()) {
    if (event instanceof ActivitiErrorEvent) {
      if (errorEvent == null) {
        errorEvent=(ActivitiErrorEvent)event;
      }
 else {
        fail("Only one ActivityErrorEvent expected");
      }
    }
  }
  assertNotNull(errorEvent);
  assertEquals(ActivitiEventType.ACTIVITY_ERROR_RECEIVED,errorEvent.getType());
  assertEquals("catchError",errorEvent.getActivityId());
  assertEquals("myError",errorEvent.getErrorId());
  assertEquals("123",errorEvent.getErrorCode());
  assertEquals(processInstance.getId(),errorEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),errorEvent.getProcessDefinitionId());
  assertFalse(processInstance.getId().equals(errorEvent.getExecutionId()));
}
