/** 
 * Test create, update and delete events of users.
 */
public void testAttachmentEntityEventsStandaloneTask() throws Exception {
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=null;
    try {
      task=taskService.newTask();
      taskService.saveTask(task);
      assertNotNull(task);
      Attachment attachment=taskService.createAttachment("test",task.getId(),null,"attachment name","description","http://activiti.org");
      assertEquals(2,listener.getEventsReceived().size());
      ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
      assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
      assertNull(event.getProcessInstanceId());
      assertNull(event.getExecutionId());
      assertNull(event.getProcessDefinitionId());
      Attachment attachmentFromEvent=(Attachment)event.getEntity();
      assertEquals(attachment.getId(),attachmentFromEvent.getId());
      event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
      assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
      listener.clearEventsReceived();
      attachment=taskService.createAttachment("test",task.getId(),null,"attachment name","description",new ByteArrayInputStream("test".getBytes()));
      assertEquals(2,listener.getEventsReceived().size());
      event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
      assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
      assertNull(event.getProcessInstanceId());
      assertNull(event.getExecutionId());
      assertNull(event.getProcessDefinitionId());
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
      assertNull(event.getProcessInstanceId());
      assertNull(event.getExecutionId());
      assertNull(event.getProcessDefinitionId());
      attachmentFromEvent=(Attachment)event.getEntity();
      assertEquals(attachment.getId(),attachmentFromEvent.getId());
      assertEquals("Description",attachmentFromEvent.getDescription());
      listener.clearEventsReceived();
      taskService.deleteAttachment(attachment.getId());
      assertEquals(1,listener.getEventsReceived().size());
      event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
      assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
      assertNull(event.getProcessInstanceId());
      assertNull(event.getExecutionId());
      assertNull(event.getProcessDefinitionId());
      attachmentFromEvent=(Attachment)event.getEntity();
      assertEquals(attachment.getId(),attachmentFromEvent.getId());
    }
  finally {
      if (task != null && task.getId() != null) {
        taskService.deleteTask(task.getId());
        historyService.deleteHistoricTaskInstance(task.getId());
      }
    }
  }
}
