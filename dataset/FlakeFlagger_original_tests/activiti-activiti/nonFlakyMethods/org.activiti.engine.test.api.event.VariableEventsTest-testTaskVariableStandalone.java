/** 
 * Test to check create, update an delete behavior for variables on a task not related to a process.
 */
public void testTaskVariableStandalone() throws Exception {
  Task newTask=taskService.newTask();
  try {
    taskService.saveTask(newTask);
    taskService.setVariable(newTask.getId(),"testVariable",123);
    taskService.setVariable(newTask.getId(),"testVariable",456);
    taskService.removeVariable(newTask.getId(),"testVariable");
    assertEquals(3,listener.getEventsReceived().size());
    ActivitiVariableEvent event=(ActivitiVariableEvent)listener.getEventsReceived().get(0);
    assertEquals(ActivitiEventType.VARIABLE_CREATED,event.getType());
    assertNull(event.getProcessDefinitionId());
    assertNull(event.getExecutionId());
    assertNull(event.getProcessInstanceId());
    assertEquals(newTask.getId(),event.getTaskId());
    assertEquals("testVariable",event.getVariableName());
    assertEquals(123,event.getVariableValue());
    event=(ActivitiVariableEvent)listener.getEventsReceived().get(1);
    assertEquals(ActivitiEventType.VARIABLE_UPDATED,event.getType());
    assertNull(event.getProcessDefinitionId());
    assertNull(event.getExecutionId());
    assertNull(event.getProcessInstanceId());
    assertEquals(newTask.getId(),event.getTaskId());
    assertEquals("testVariable",event.getVariableName());
    assertEquals(456,event.getVariableValue());
    event=(ActivitiVariableEvent)listener.getEventsReceived().get(2);
    assertEquals(ActivitiEventType.VARIABLE_DELETED,event.getType());
    assertNull(event.getProcessDefinitionId());
    assertNull(event.getExecutionId());
    assertNull(event.getProcessInstanceId());
    assertEquals(newTask.getId(),event.getTaskId());
    assertEquals("testVariable",event.getVariableName());
    assertEquals(null,event.getVariableValue());
  }
  finally {
    if (newTask.getId() != null) {
      taskService.deleteTask(newTask.getId());
      if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
        historyService.deleteHistoricTaskInstance(newTask.getId());
      }
    }
  }
}
