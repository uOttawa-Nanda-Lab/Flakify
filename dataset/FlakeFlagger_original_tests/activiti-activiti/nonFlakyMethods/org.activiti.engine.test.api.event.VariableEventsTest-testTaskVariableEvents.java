/** 
 * Test create, update and delete of task-local variables.
 */
@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testTaskVariableEvents() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(processInstance);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.setVariableLocal(task.getId(),"testVariable","The value");
  taskService.setVariableLocal(task.getId(),"testVariable","Updated value");
  taskService.removeVariableLocal(task.getId(),"testVariable");
  assertEquals(3,listener.getEventsReceived().size());
  ActivitiVariableEvent event=(ActivitiVariableEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.VARIABLE_CREATED,event.getType());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertEquals(task.getId(),event.getTaskId());
  assertEquals("testVariable",event.getVariableName());
  assertEquals("The value",event.getVariableValue());
  event=(ActivitiVariableEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.VARIABLE_UPDATED,event.getType());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertEquals(task.getId(),event.getTaskId());
  assertEquals("testVariable",event.getVariableName());
  assertEquals("Updated value",event.getVariableValue());
  event=(ActivitiVariableEvent)listener.getEventsReceived().get(2);
  assertEquals(ActivitiEventType.VARIABLE_DELETED,event.getType());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  assertEquals(task.getId(),event.getTaskId());
  assertEquals("testVariable",event.getVariableName());
  assertEquals(null,event.getVariableValue());
  listener.clearEventsReceived();
}
