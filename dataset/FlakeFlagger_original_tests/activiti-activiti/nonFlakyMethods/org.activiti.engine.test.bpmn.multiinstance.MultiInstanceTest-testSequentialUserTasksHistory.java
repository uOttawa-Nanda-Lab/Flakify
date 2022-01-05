@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.sequentialUserTasks.bpmn20.xml"}) public void testSequentialUserTasksHistory(){
  String procId=runtimeService.startProcessInstanceByKey("miSequentialUserTasks",CollectionUtil.singletonMap(NR_OF_LOOPS_KEY,4)).getId();
  for (int i=0; i < 4; i++) {
    taskService.complete(taskService.createTaskQuery().singleResult().getId());
  }
  assertProcessEnded(procId);
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricTaskInstance> historicTaskInstances=historyService.createHistoricTaskInstanceQuery().list();
    assertEquals(4,historicTaskInstances.size());
    for (    HistoricTaskInstance ht : historicTaskInstances) {
      assertNotNull(ht.getAssignee());
      assertNotNull(ht.getStartTime());
      assertNotNull(ht.getEndTime());
    }
    List<HistoricActivityInstance> historicActivityInstances=historyService.createHistoricActivityInstanceQuery().activityType("userTask").list();
    assertEquals(4,historicActivityInstances.size());
    for (    HistoricActivityInstance hai : historicActivityInstances) {
      assertNotNull(hai.getActivityId());
      assertNotNull(hai.getActivityName());
      assertNotNull(hai.getStartTime());
      assertNotNull(hai.getEndTime());
      assertNotNull(hai.getAssignee());
    }
  }
}
