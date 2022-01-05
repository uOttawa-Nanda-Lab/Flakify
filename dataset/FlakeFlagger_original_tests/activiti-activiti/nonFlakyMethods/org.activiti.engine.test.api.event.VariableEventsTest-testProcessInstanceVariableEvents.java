/** 
 * Test create, update and delete variables on a process-instance, using the API.
 */
@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testProcessInstanceVariableEvents() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(processInstance);
  runtimeService.setVariable(processInstance.getId(),"testVariable","The value");
  assertEquals(1,listener.getEventsReceived().size());
  ActivitiVariableEvent event=(ActivitiVariableEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.VARIABLE_CREATED,event.getType());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  assertEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertNull(event.getTaskId());
  assertEquals("testVariable",event.getVariableName());
  assertEquals("The value",event.getVariableValue());
  listener.clearEventsReceived();
  runtimeService.setVariable(processInstance.getId(),"testVariable","Updated value");
  assertEquals(1,listener.getEventsReceived().size());
  event=(ActivitiVariableEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.VARIABLE_UPDATED,event.getType());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  assertEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertNull(event.getTaskId());
  assertEquals("testVariable",event.getVariableName());
  assertEquals("Updated value",event.getVariableValue());
  listener.clearEventsReceived();
  runtimeService.removeVariable(processInstance.getId(),"testVariable");
  assertEquals(1,listener.getEventsReceived().size());
  event=(ActivitiVariableEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.VARIABLE_DELETED,event.getType());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  assertEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertNull(event.getTaskId());
  assertEquals("testVariable",event.getVariableName());
  assertEquals(null,event.getVariableValue());
  listener.clearEventsReceived();
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("test",123);
  vars.put("test2",456);
  runtimeService.setVariables(processInstance.getId(),vars);
  runtimeService.setVariables(processInstance.getId(),vars);
  runtimeService.removeVariables(processInstance.getId(),vars.keySet());
  assertEquals(6,listener.getEventsReceived().size());
  assertEquals(ActivitiEventType.VARIABLE_CREATED,listener.getEventsReceived().get(0).getType());
  assertEquals(ActivitiEventType.VARIABLE_CREATED,listener.getEventsReceived().get(1).getType());
  assertEquals(ActivitiEventType.VARIABLE_UPDATED,listener.getEventsReceived().get(2).getType());
  assertEquals(ActivitiEventType.VARIABLE_UPDATED,listener.getEventsReceived().get(3).getType());
  assertEquals(ActivitiEventType.VARIABLE_DELETED,listener.getEventsReceived().get(4).getType());
  assertEquals(ActivitiEventType.VARIABLE_DELETED,listener.getEventsReceived().get(5).getType());
  listener.clearEventsReceived();
  runtimeService.removeVariable(processInstance.getId(),"unexistingVariable");
  assertTrue(listener.getEventsReceived().isEmpty());
}
