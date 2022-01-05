/** 
 * Test create, update and delete events of process definitions.
 */
@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testProcessDefinitionEvents() throws Exception {
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").singleResult();
  assertNotNull(processDefinition);
  assertEquals(2,listener.getEventsReceived().size());
  assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiEntityEvent);
  ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
  assertEquals(processDefinition.getId(),((ProcessDefinition)event.getEntity()).getId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
  assertEquals(processDefinition.getId(),((ProcessDefinition)event.getEntity()).getId());
  listener.clearEventsReceived();
  repositoryService.setProcessDefinitionCategory(processDefinition.getId(),"test");
  assertEquals(1,listener.getEventsReceived().size());
  assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiEntityEvent);
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ENTITY_UPDATED,event.getType());
  assertEquals(processDefinition.getId(),((ProcessDefinition)event.getEntity()).getId());
  assertEquals("test",((ProcessDefinition)event.getEntity()).getCategory());
  listener.clearEventsReceived();
  repositoryService.suspendProcessDefinitionById(processDefinition.getId());
  repositoryService.activateProcessDefinitionById(processDefinition.getId());
  assertEquals(2,listener.getEventsReceived().size());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(processDefinition.getId(),((ProcessDefinition)event.getEntity()).getId());
  assertEquals(ActivitiEventType.ENTITY_SUSPENDED,event.getType());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_ACTIVATED,event.getType());
  assertEquals(processDefinition.getId(),((ProcessDefinition)event.getEntity()).getId());
  listener.clearEventsReceived();
  repositoryService.deleteDeployment(processDefinition.getDeploymentId(),true);
  deploymentIdFromDeploymentAnnotation=null;
  assertEquals(1,listener.getEventsReceived().size());
  assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiEntityEvent);
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
  assertEquals(processDefinition.getId(),((ProcessDefinition)event.getEntity()).getId());
  listener.clearEventsReceived();
}
