/** 
 * Test create, update and delete events of model entities.
 */
public void testModelEvents() throws Exception {
  Model model=null;
  try {
    model=repositoryService.newModel();
    model.setName("My model");
    model.setKey("key");
    repositoryService.saveModel(model);
    assertEquals(2,listener.getEventsReceived().size());
    assertEquals(ActivitiEventType.ENTITY_CREATED,listener.getEventsReceived().get(0).getType());
    assertEquals(model.getId(),((Model)((ActivitiEntityEvent)listener.getEventsReceived().get(0)).getEntity()).getId());
    assertEquals(ActivitiEventType.ENTITY_INITIALIZED,listener.getEventsReceived().get(1).getType());
    assertEquals(model.getId(),((Model)((ActivitiEntityEvent)listener.getEventsReceived().get(1)).getEntity()).getId());
    listener.clearEventsReceived();
    model=repositoryService.getModel(model.getId());
    model.setName("Updated");
    repositoryService.saveModel(model);
    assertEquals(1,listener.getEventsReceived().size());
    assertEquals(ActivitiEventType.ENTITY_UPDATED,listener.getEventsReceived().get(0).getType());
    assertEquals(model.getId(),((Model)((ActivitiEntityEvent)listener.getEventsReceived().get(0)).getEntity()).getId());
    listener.clearEventsReceived();
    repositoryService.addModelEditorSource(model.getId(),"test".getBytes());
    repositoryService.addModelEditorSourceExtra(model.getId(),"test extra".getBytes());
    assertEquals(2,listener.getEventsReceived().size());
    assertEquals(ActivitiEventType.ENTITY_UPDATED,listener.getEventsReceived().get(0).getType());
    assertEquals(ActivitiEventType.ENTITY_UPDATED,listener.getEventsReceived().get(1).getType());
    listener.clearEventsReceived();
    repositoryService.deleteModel(model.getId());
    assertEquals(1,listener.getEventsReceived().size());
    assertEquals(ActivitiEventType.ENTITY_DELETED,listener.getEventsReceived().get(0).getType());
    assertEquals(model.getId(),((Model)((ActivitiEntityEvent)listener.getEventsReceived().get(0)).getEntity()).getId());
    listener.clearEventsReceived();
  }
  finally {
    if (model != null && repositoryService.getModel(model.getId()) != null) {
      repositoryService.deleteModel(model.getId());
    }
  }
}
