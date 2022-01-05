@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testParallelCallActivity.bpmn20.xml","org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.externalSubProcess.bpmn20.xml"}) public void testParallelCallActivityHistory(){
  runtimeService.startProcessInstanceByKey("miParallelCallActivity");
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(12,tasks.size());
  for (int i=0; i < tasks.size(); i++) {
    taskService.complete(tasks.get(i).getId());
  }
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricProcessInstance> historicProcessInstances=historyService.createHistoricProcessInstanceQuery().list();
    assertEquals(7,historicProcessInstances.size());
    for (    HistoricProcessInstance hpi : historicProcessInstances) {
      assertNotNull(hpi.getStartTime());
      assertNotNull(hpi.getEndTime());
    }
    List<HistoricTaskInstance> historicTaskInstances=historyService.createHistoricTaskInstanceQuery().list();
    assertEquals(12,historicTaskInstances.size());
    for (    HistoricTaskInstance hti : historicTaskInstances) {
      assertNotNull(hti.getStartTime());
      assertNotNull(hti.getEndTime());
      assertNotNull(hti.getAssignee());
      assertNull(hti.getDeleteReason());
    }
    List<HistoricActivityInstance> historicActivityInstances=historyService.createHistoricActivityInstanceQuery().activityType("callActivity").list();
    assertEquals(6,historicActivityInstances.size());
    for (    HistoricActivityInstance hai : historicActivityInstances) {
      assertNotNull(hai.getStartTime());
      assertNotNull(hai.getEndTime());
    }
  }
}
