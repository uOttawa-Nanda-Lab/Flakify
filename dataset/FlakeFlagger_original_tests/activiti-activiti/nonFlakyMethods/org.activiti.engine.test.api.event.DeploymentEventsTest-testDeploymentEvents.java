/** 
 * Test create, update and delete events of deployment entities.
 */
public void testDeploymentEvents() throws Exception {
  Deployment deployment=null;
  try {
    listener.clearEventsReceived();
    deployment=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml").deploy();
    assertNotNull(deployment);
    assertEquals(2,listener.getEventsReceived().size());
    assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiEntityEvent);
    ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
    assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
    assertEquals(deployment.getId(),((Deployment)event.getEntity()).getId());
    assertTrue(listener.getEventsReceived().get(1) instanceof ActivitiEntityEvent);
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
    assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
    assertEquals(deployment.getId(),((Deployment)event.getEntity()).getId());
    listener.clearEventsReceived();
    repositoryService.setDeploymentCategory(deployment.getId(),"test");
    assertEquals(1,listener.getEventsReceived().size());
    assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiEntityEvent);
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
    assertEquals(ActivitiEventType.ENTITY_UPDATED,event.getType());
    assertEquals(deployment.getId(),((Deployment)event.getEntity()).getId());
    assertEquals("test",((Deployment)event.getEntity()).getCategory());
    listener.clearEventsReceived();
    repositoryService.deleteDeployment(deployment.getId(),true);
    assertEquals(1,listener.getEventsReceived().size());
    assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiEntityEvent);
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
    assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
    assertEquals(deployment.getId(),((Deployment)event.getEntity()).getId());
    listener.clearEventsReceived();
  }
  finally {
    if (deployment != null && repositoryService.createDeploymentQuery().deploymentId(deployment.getId()).count() > 0) {
      repositoryService.deleteDeployment(deployment.getId(),true);
    }
  }
}
