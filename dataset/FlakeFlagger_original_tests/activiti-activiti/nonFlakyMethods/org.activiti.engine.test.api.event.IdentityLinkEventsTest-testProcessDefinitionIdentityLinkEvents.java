/** 
 * Check identity links on process definitions.
 */
@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testProcessDefinitionIdentityLinkEvents() throws Exception {
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").singleResult();
  assertNotNull(processDefinition);
  repositoryService.addCandidateStarterUser(processDefinition.getId(),"kermit");
  repositoryService.addCandidateStarterGroup(processDefinition.getId(),"sales");
  assertEquals(4,listener.getEventsReceived().size());
  ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
  assertTrue(event.getEntity() instanceof IdentityLink);
  assertEquals(processDefinition.getId(),event.getProcessDefinitionId());
  assertNull(event.getProcessInstanceId());
  assertNull(event.getExecutionId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(2);
  assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
  assertTrue(event.getEntity() instanceof IdentityLink);
  assertEquals(processDefinition.getId(),event.getProcessDefinitionId());
  assertNull(event.getProcessInstanceId());
  assertNull(event.getExecutionId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(3);
  assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
  listener.clearEventsReceived();
  repositoryService.deleteCandidateStarterUser(processDefinition.getId(),"kermit");
  repositoryService.deleteCandidateStarterGroup(processDefinition.getId(),"sales");
  assertEquals(2,listener.getEventsReceived().size());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
  assertTrue(event.getEntity() instanceof IdentityLink);
  assertEquals(processDefinition.getId(),event.getProcessDefinitionId());
  assertNull(event.getProcessInstanceId());
  assertNull(event.getExecutionId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
  assertTrue(event.getEntity() instanceof IdentityLink);
  assertEquals(processDefinition.getId(),event.getProcessDefinitionId());
  assertNull(event.getProcessInstanceId());
  assertNull(event.getExecutionId());
}
