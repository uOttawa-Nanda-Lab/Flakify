/** 
 * Test to verify if signals coming from an intermediate throw-event trigger the right events to be dispatched.
 */
@Deployment public void testActivitySignalEventsWithinProcess() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("signalProcess");
  assertNotNull(processInstance);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  Execution executionWithSignalEvent=runtimeService.createExecutionQuery().activityId("shipOrder").singleResult();
  taskService.complete(task.getId());
  assertEquals(1L,listener.getEventsReceived().size());
  assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiSignalEvent);
  ActivitiSignalEvent signalEvent=(ActivitiSignalEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ACTIVITY_SIGNALED,signalEvent.getType());
  assertEquals("shipOrder",signalEvent.getActivityId());
  assertEquals(executionWithSignalEvent.getId(),signalEvent.getExecutionId());
  assertEquals(executionWithSignalEvent.getProcessInstanceId(),signalEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),signalEvent.getProcessDefinitionId());
  assertEquals("alert",signalEvent.getSignalName());
  assertNull(signalEvent.getSignalData());
}
