@Deployment public void testTaskPropertiesNotNull(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task.getId());
  assertEquals("my task",task.getName());
  assertEquals("Very important",task.getDescription());
  assertTrue(task.getPriority() > 0);
  assertEquals("kermit",task.getAssignee());
  assertEquals(processInstance.getId(),task.getProcessInstanceId());
  assertNotNull(task.getProcessDefinitionId());
  assertNotNull(task.getTaskDefinitionKey());
  assertNotNull(task.getCreateTime());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    assertEquals(0,taskService.getTaskEvents(task.getId()).size());
  }
}
