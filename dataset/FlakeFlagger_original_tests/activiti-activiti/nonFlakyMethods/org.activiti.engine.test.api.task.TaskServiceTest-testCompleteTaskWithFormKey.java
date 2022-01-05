@Deployment(resources={"org/activiti/engine/test/api/oneTaskWithFormKeyProcess.bpmn20.xml"}) public void testCompleteTaskWithFormKey(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskWithFormProcess");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("my task",task.getName());
  assertEquals("myFormKey",task.getFormKey());
  assertEquals("myAssignee",task.getAssignee());
  assertEquals("myOwner",task.getOwner());
  assertEquals("myCategory",task.getCategory());
  assertEquals(60,task.getPriority());
  assertNotNull(task.getDueDate());
  taskService.complete(task.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricTaskInstance historicTask=historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).singleResult();
    assertEquals("my task",historicTask.getName());
    assertEquals("myFormKey",historicTask.getFormKey());
    assertEquals("myAssignee",historicTask.getAssignee());
    assertEquals("myOwner",historicTask.getOwner());
    assertEquals("myCategory",historicTask.getCategory());
    assertEquals(60,historicTask.getPriority());
    assertNotNull(historicTask.getDueDate());
  }
}
