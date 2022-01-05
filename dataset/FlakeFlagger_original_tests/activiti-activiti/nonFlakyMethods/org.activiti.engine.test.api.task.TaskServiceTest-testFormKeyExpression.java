@Deployment public void testFormKeyExpression(){
  runtimeService.startProcessInstanceByKey("testFormExpression",CollectionUtil.singletonMap("var","abc"));
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("first-form.json",task.getFormKey());
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().singleResult();
  assertEquals("form-abc.json",task.getFormKey());
  task.setFormKey("form-changed.json");
  taskService.saveTask(task);
  task=taskService.createTaskQuery().singleResult();
  assertEquals("form-changed.json",task.getFormKey());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    HistoricTaskInstance historicTaskInstance=historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).singleResult();
    assertEquals("form-changed.json",historicTaskInstance.getFormKey());
  }
}
