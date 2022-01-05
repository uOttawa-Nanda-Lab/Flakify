/** 
 * Test to verify listeners defined in the BPMN xml are added to the process definition and are active.
 */
@Deployment public void testProcessDefinitionListenerDefinition() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testEventListeners");
  assertNotNull(testListenerBean);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  assertFalse(testListenerBean.getEventsReceived().isEmpty());
  for (  ActivitiEvent event : testListenerBean.getEventsReceived()) {
    assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
  }
  assertTrue(testListenerBean.getEventsReceived().get(0) instanceof ActivitiEntityEvent);
  ActivitiEntityEvent event=(ActivitiEntityEvent)testListenerBean.getEventsReceived().get(0);
  assertTrue(event.getEntity() instanceof ProcessInstance);
  assertEquals(processInstance.getId(),((ProcessInstance)event.getEntity()).getId());
  List<ActivitiEvent> events=StaticTestActivitiEventListener.getEventsReceived();
  assertFalse(events.isEmpty());
  boolean insertFound=false;
  boolean deleteFound=false;
  for (  ActivitiEvent e : events) {
    if (ActivitiEventType.ENTITY_CREATED == e.getType()) {
      insertFound=true;
    }
 else     if (ActivitiEventType.ENTITY_DELETED == e.getType()) {
      deleteFound=true;
    }
  }
  assertTrue(insertFound);
  assertTrue(deleteFound);
}
