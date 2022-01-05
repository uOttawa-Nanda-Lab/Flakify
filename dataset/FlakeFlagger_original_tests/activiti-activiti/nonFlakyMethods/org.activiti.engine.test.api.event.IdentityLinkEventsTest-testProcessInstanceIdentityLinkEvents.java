/** 
 * Check identity links on process instances.
 */
@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testProcessInstanceIdentityLinkEvents() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  runtimeService.addUserIdentityLink(processInstance.getId(),"kermit","test");
  assertEquals(2,listener.getEventsReceived().size());
  ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
  assertTrue(event.getEntity() instanceof IdentityLink);
  assertEquals(processInstance.getId(),event.getProcessInstanceId());
  assertEquals(processInstance.getId(),event.getExecutionId());
  assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
  IdentityLink link=(IdentityLink)event.getEntity();
  assertEquals("kermit",link.getUserId());
  assertEquals("test",link.getType());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
  listener.clearEventsReceived();
  runtimeService.deleteProcessInstance(processInstance.getId(),"test");
  assertEquals(1,listener.getEventsReceived().size());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
  assertTrue(event.getEntity() instanceof IdentityLink);
  link=(IdentityLink)event.getEntity();
  assertEquals("kermit",link.getUserId());
  assertEquals("test",link.getType());
}
