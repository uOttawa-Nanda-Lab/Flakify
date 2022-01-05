/** 
 * Test events related to compensation events.
 */
@Deployment public void testActivityCompensationEvents() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("compensationProcess");
  assertNotNull(processInstance);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertEquals(1,listener.getEventsReceived().size());
  assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiActivityEvent);
  ActivitiActivityEvent activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ACTIVITY_COMPENSATE,activityEvent.getType());
  assertEquals("compensate",activityEvent.getActivityId());
  assertFalse(processInstance.getId().equals(activityEvent.getExecutionId()));
  assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());
  processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(processInstance);
  assertDatabaseEventPresent(ActivitiEventType.ACTIVITY_COMPENSATE);
}
