@Deployment public void testRegularProcessInstanceEnd(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("deleteReasonProcess");
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  while (!tasks.isEmpty()) {
    for (    Task task : tasks) {
      taskService.complete(task.getId());
    }
    tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  }
  assertEquals(0L,runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).count());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertNull(historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult().getDeleteReason());
    List<HistoricTaskInstance> historicTaskInstances=historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).list();
    assertEquals(5,historicTaskInstances.size());
    for (    HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
      assertNull(historicTaskInstance.getDeleteReason());
    }
    assertHistoricActivitiesDeleteReason(processInstance,null,"A","B","C","D","E");
  }
}
