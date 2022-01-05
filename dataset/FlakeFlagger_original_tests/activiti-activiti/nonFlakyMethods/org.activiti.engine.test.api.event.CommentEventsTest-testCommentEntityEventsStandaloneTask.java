public void testCommentEntityEventsStandaloneTask() throws Exception {
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    Task task=null;
    try {
      task=taskService.newTask();
      taskService.saveTask(task);
      assertNotNull(task);
      Comment comment=taskService.addComment(task.getId(),null,"comment");
      assertEquals(2,listener.getEventsReceived().size());
      ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
      assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
      assertNull(event.getProcessInstanceId());
      assertNull(event.getExecutionId());
      assertNull(event.getProcessDefinitionId());
      Comment commentFromEvent=(Comment)event.getEntity();
      assertEquals(comment.getId(),commentFromEvent.getId());
      event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
      assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
      listener.clearEventsReceived();
      taskService.deleteComment(comment.getId());
      assertEquals(1,listener.getEventsReceived().size());
      event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
      assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
      assertNull(event.getProcessInstanceId());
      assertNull(event.getExecutionId());
      assertNull(event.getProcessDefinitionId());
      commentFromEvent=(Comment)event.getEntity();
      assertEquals(comment.getId(),commentFromEvent.getId());
    }
  finally {
      if (task != null && task.getId() != null) {
        taskService.deleteTask(task.getId());
        historyService.deleteHistoricTaskInstance(task.getId());
      }
    }
  }
}
