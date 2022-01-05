/** 
 * Test variable events when done within a process (eg. execution-listener)
 */
@Deployment public void testTaskVariableEventsWithinProcess() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("variableProcess");
  assertNotNull(processInstance);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  assertEquals(3,listener.getEventsReceived().size());
  ActivitiVariableEvent event=(ActivitiVariableEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.VARIABLE_CREATED,event.getType());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertEquals(task.getId(),event.getTaskId());
  assertEquals("variable",event.getVariableName());
  assertEquals(123,event.getVariableValue());
  event=(ActivitiVariableEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.VARIABLE_UPDATED,event.getType());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertEquals(task.getId(),event.getTaskId());
  assertEquals("variable",event.getVariableName());
  assertEquals(456,event.getVariableValue());
  event=(ActivitiVariableEvent)listener.getEventsReceived().get(2);
  assertEquals(ActivitiEventType.VARIABLE_DELETED,event.getType());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertEquals(task.getId(),event.getTaskId());
  assertEquals("variable",event.getVariableName());
  assertEquals(null,event.getVariableValue());
}
