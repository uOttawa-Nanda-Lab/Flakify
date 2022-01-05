/** 
 * Test create event of variables when process is started with variables passed in.
 */
@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testProcessInstanceVariableEventsOnStart() throws Exception {
  HashMap<String,Object> vars=new HashMap<String,Object>();
  vars.put("testVariable","The value");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  assertNotNull(processInstance);
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
}
