/** 
 * Test create, update and delete variables locally on a child-execution of the process instance.
 */
@Deployment public void testProcessInstanceVariableEventsOnChildExecution() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("variableProcess");
  assertNotNull(processInstance);
  Execution child=runtimeService.createExecutionQuery().parentId(processInstance.getId()).singleResult();
  assertNotNull(child);
  runtimeService.setVariableLocal(child.getId(),"test",1234567);
  assertEquals(1,listener.getEventsReceived().size());
  ActivitiVariableEvent event=(ActivitiVariableEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.VARIABLE_CREATED,event.getType());
  assertEquals(child.getId(),event.getExecutionId());
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
}
