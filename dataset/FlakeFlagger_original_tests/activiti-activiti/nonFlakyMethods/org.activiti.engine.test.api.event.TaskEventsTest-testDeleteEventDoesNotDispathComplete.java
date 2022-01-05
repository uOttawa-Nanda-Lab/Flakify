/** 
 * Check events related to process instance delete and standalone task delete.
 */
@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testDeleteEventDoesNotDispathComplete() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(processInstance);
  listener.clearEventsReceived();
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  runtimeService.deleteProcessInstance(processInstance.getId(),"testing task delete events");
  assertEquals(1,listener.getEventsReceived().size());
  ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
  assertTrue(event.getEntity() instanceof Task);
  Task taskFromEvent=(Task)event.getEntity();
  assertEquals(task.getId(),taskFromEvent.getId());
  assertExecutionDetails(event,processInstance);
  try {
    task=taskService.newTask();
    task.setCategory("123");
    task.setDescription("Description");
    taskService.saveTask(task);
    listener.clearEventsReceived();
    taskService.deleteTask(task.getId());
    assertEquals(1,listener.getEventsReceived().size());
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
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
