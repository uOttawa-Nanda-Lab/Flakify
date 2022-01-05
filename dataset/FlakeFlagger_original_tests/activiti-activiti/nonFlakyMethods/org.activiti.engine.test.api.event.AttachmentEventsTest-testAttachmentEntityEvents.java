/** 
 * Test create, update and delete events of attachments on a task/process.
 */
@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testAttachmentEntityEvents() throws Exception {
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
    Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    assertNotNull(task);
    Attachment attachment=taskService.createAttachment("test",task.getId(),processInstance.getId(),"attachment name","description","http://activiti.org");
    assertNull(attachment.getUserId());
    assertEquals(2,listener.getEventsReceived().size());
    ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
    assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
    assertEquals(processInstance.getId(),event.getProcessInstanceId());
    assertEquals(processInstance.getId(),event.getExecutionId());
    assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
    Attachment attachmentFromEvent=(Attachment)event.getEntity();
    assertEquals(attachment.getId(),attachmentFromEvent.getId());
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
    assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
    assertEquals(processInstance.getId(),event.getProcessInstanceId());
    assertEquals(processInstance.getId(),event.getExecutionId());
    assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
    attachmentFromEvent=(Attachment)event.getEntity();
    assertEquals(attachment.getId(),attachmentFromEvent.getId());
    listener.clearEventsReceived();
    Authentication.setAuthenticatedUserId("testuser");
    attachment=taskService.createAttachment("test",task.getId(),processInstance.getId(),"attachment name","description",new ByteArrayInputStream("test".getBytes()));
    assertNotNull(attachment.getUserId());
    assertEquals("testuser",attachment.getUserId());
    assertEquals(2,listener.getEventsReceived().size());
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
    assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
    assertEquals(processInstance.getId(),event.getProcessInstanceId());
    assertEquals(processInstance.getId(),event.getExecutionId());
    assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
    attachmentFromEvent=(Attachment)event.getEntity();
    assertEquals(attachment.getId(),attachmentFromEvent.getId());
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
    assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
    listener.clearEventsReceived();
    attachment=taskService.getAttachment(attachment.getId());
    attachment.setDescription("Description");
    taskService.saveAttachment(attachment);
    assertEquals(1,listener.getEventsReceived().size());
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
    assertEquals(ActivitiEventType.ENTITY_UPDATED,event.getType());
    assertEquals(processInstance.getId(),event.getProcessInstanceId());
    assertEquals(processInstance.getId(),event.getExecutionId());
    assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
    attachmentFromEvent=(Attachment)event.getEntity();
    assertEquals(attachment.getId(),attachmentFromEvent.getId());
    assertEquals("Description",attachmentFromEvent.getDescription());
    listener.clearEventsReceived();
    taskService.deleteAttachment(attachment.getId());
    assertEquals(1,listener.getEventsReceived().size());
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
    assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
    assertEquals(processInstance.getId(),event.getProcessInstanceId());
    assertEquals(processInstance.getId(),event.getExecutionId());
    assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
    attachmentFromEvent=(Attachment)event.getEntity();
    assertEquals(attachment.getId(),attachmentFromEvent.getId());
  }
}
