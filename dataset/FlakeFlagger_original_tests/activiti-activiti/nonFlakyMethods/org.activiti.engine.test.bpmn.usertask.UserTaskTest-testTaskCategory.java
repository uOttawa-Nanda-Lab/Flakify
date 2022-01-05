@Deployment public void testTaskCategory(){
  runtimeService.startProcessInstanceByKey("testTaskCategory");
  Task task=taskService.createTaskQuery().singleResult();
  String testCategory="My Category";
  assertEquals(testCategory,task.getCategory());
  assertEquals("Task with category",taskService.createTaskQuery().taskCategory(testCategory).singleResult().getName());
  assertTrue(taskService.createTaskQuery().taskCategory("Does not exist").count() == 0);
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    HistoricTaskInstance historicTaskInstance=historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).singleResult();
    assertEquals(testCategory,historicTaskInstance.getCategory());
    assertEquals("Task with category",historyService.createHistoricTaskInstanceQuery().taskCategory(testCategory).singleResult().getName());
    assertTrue(historyService.createHistoricTaskInstanceQuery().taskCategory("Does not exist").count() == 0);
    String newCategory="New Test Category";
    task.setCategory(newCategory);
    taskService.saveTask(task);
    task=taskService.createTaskQuery().singleResult();
    assertEquals(newCategory,task.getCategory());
    assertEquals("Task with category",taskService.createTaskQuery().taskCategory(newCategory).singleResult().getName());
    assertTrue(taskService.createTaskQuery().taskCategory(testCategory).count() == 0);
    taskService.complete(task.getId());
    historicTaskInstance=historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).singleResult();
    assertEquals(newCategory,historicTaskInstance.getCategory());
    assertEquals("Task with category",historyService.createHistoricTaskInstanceQuery().taskCategory(newCategory).singleResult().getName());
    assertTrue(historyService.createHistoricTaskInstanceQuery().taskCategory(testCategory).count() == 0);
  }
}
