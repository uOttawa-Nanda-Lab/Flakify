public void testAttachmentEntityEventsOnHistoricTaskDelete() throws Exception {
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=null;
    try {
      task=taskService.newTask();
      taskService.saveTask(task);
      assertNotNull(task);
      Attachment attachment=taskService.createAttachment("test",task.getId(),null,"attachment name","description","http://activiti.org");
      listener.clearEventsReceived();
      taskService.deleteTask(task.getId());
      historyService.deleteHistoricTaskInstance(task.getId());
      assertEquals(1,listener.getEventsReceived().size());
      ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
      assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
      assertNull(event.getProcessInstanceId());
      assertNull(event.getExecutionId());
      assertNull(event.getProcessDefinitionId());
      Attachment attachmentFromEvent=(Attachment)event.getEntity();
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
