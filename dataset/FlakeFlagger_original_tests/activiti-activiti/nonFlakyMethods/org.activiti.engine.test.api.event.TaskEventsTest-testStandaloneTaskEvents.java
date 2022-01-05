/** 
 * Check all events for tasks not related to a process-instance
 */
public void testStandaloneTaskEvents() throws Exception {
  Task task=null;
  try {
    task=taskService.newTask();
    task.setCategory("123");
    task.setDescription("Description");
    taskService.saveTask(task);
    assertEquals(3,listener.getEventsReceived().size());
    ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
    assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
    assertTrue(event.getEntity() instanceof Task);
    Task taskFromEvent=(Task)event.getEntity();
    assertEquals(task.getId(),taskFromEvent.getId());
    assertNull(event.getProcessDefinitionId());
    assertNull(event.getProcessInstanceId());
    assertNull(event.getExecutionId());
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
    assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(2);
    assertEquals(ActivitiEventType.TASK_CREATED,event.getType());
    listener.clearEventsReceived();
    taskService.setOwner(task.getId(),"owner");
    assertEquals(1,listener.getEventsReceived().size());
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
    assertEquals(ActivitiEventType.ENTITY_UPDATED,event.getType());
    assertTrue(event.getEntity() instanceof Task);
    taskFromEvent=(Task)event.getEntity();
    assertEquals(task.getId(),taskFromEvent.getId());
    assertEquals("owner",taskFromEvent.getOwner());
    assertNull(event.getProcessDefinitionId());
    assertNull(event.getProcessInstanceId());
    assertNull(event.getExecutionId());
    listener.clearEventsReceived();
    taskService.setAssignee(task.getId(),"kermit");
    assertEquals(2,listener.getEventsReceived().size());
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
    assertEquals(ActivitiEventType.TASK_ASSIGNED,event.getType());
    assertTrue(event.getEntity() instanceof Task);
    taskFromEvent=(Task)event.getEntity();
    assertEquals(task.getId(),taskFromEvent.getId());
    assertEquals("kermit",taskFromEvent.getAssignee());
    assertNull(event.getProcessDefinitionId());
    assertNull(event.getProcessInstanceId());
    assertNull(event.getExecutionId());
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
    assertEquals(ActivitiEventType.ENTITY_UPDATED,event.getType());
    assertTrue(event.getEntity() instanceof Task);
    taskFromEvent=(Task)event.getEntity();
    assertEquals(task.getId(),taskFromEvent.getId());
    assertNull(event.getProcessDefinitionId());
    assertNull(event.getProcessInstanceId());
    assertNull(event.getExecutionId());
    listener.clearEventsReceived();
    taskService.complete(task.getId());
    assertEquals(2,listener.getEventsReceived().size());
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
    assertEquals(ActivitiEventType.TASK_COMPLETED,event.getType());
    assertTrue(event.getEntity() instanceof Task);
    taskFromEvent=(Task)event.getEntity();
    assertEquals(task.getId(),taskFromEvent.getId());
    assertNull(event.getProcessDefinitionId());
    assertNull(event.getProcessInstanceId());
    assertNull(event.getExecutionId());
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
    assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
    assertTrue(event.getEntity() instanceof Task);
    taskFromEvent=(Task)event.getEntity();
    assertEquals(task.getId(),taskFromEvent.getId());
    assertNull(event.getProcessDefinitionId());
    assertNull(event.getProcessInstanceId());
    assertNull(event.getExecutionId());
  }
  finally {
    if (task != null) {
      String taskId=task.getId();
      task=taskService.createTaskQuery().taskId(taskId).singleResult();
      if (task != null) {
        taskService.deleteTask(taskId);
      }
      historyService.deleteHistoricTaskInstance(taskId);
    }
  }
}
