@Deployment public void testEndTimeOnMiSubprocess(){
  if (!processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    return;
  }
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("multiInstanceSubProcessParallelTasks");
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(2,tasks.size());
  assertEquals("User Task 1",tasks.get(0).getName());
  assertEquals("User Task 1",tasks.get(1).getName());
  List<HistoricActivityInstance> historicActivityInstances=historyService.createHistoricActivityInstanceQuery().activityId("subprocess1").list();
  assertEquals(2,historicActivityInstances.size());
  for (  HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
    assertNotNull(historicActivityInstance.getStartTime());
    assertNull(historicActivityInstance.getEndTime());
  }
  taskService.complete(tasks.get(0).getId());
  historicActivityInstances=historyService.createHistoricActivityInstanceQuery().activityId("subprocess1").list();
  assertEquals(2,historicActivityInstances.size());
  for (  HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
    assertNull(historicActivityInstance.getEndTime());
  }
  taskService.complete(tasks.get(1).getId());
  historicActivityInstances=historyService.createHistoricActivityInstanceQuery().activityId("subprocess1").list();
  assertEquals(2,historicActivityInstances.size());
  for (  HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
    assertNull(historicActivityInstance.getEndTime());
  }
  tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("User Task 3").list();
  assertEquals(2,tasks.size());
  for (  Task task : tasks) {
    taskService.complete(task.getId());
    historicActivityInstances=historyService.createHistoricActivityInstanceQuery().activityId("subprocess1").list();
    assertEquals(2,historicActivityInstances.size());
    for (    HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
      assertNull(historicActivityInstance.getEndTime());
    }
  }
  tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(2,tasks.size());
  for (  Task task : tasks) {
    taskService.complete(task.getId());
  }
  historicActivityInstances=historyService.createHistoricActivityInstanceQuery().activityId("subprocess1").list();
  assertEquals(2,historicActivityInstances.size());
  for (  HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
    assertNotNull(historicActivityInstance.getEndTime());
  }
}
