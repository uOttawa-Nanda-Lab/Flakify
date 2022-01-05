@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testParallelUserTasks.bpmn20.xml"}) public void testParallelUserTasksHistory(){
  runtimeService.startProcessInstanceByKey("miParallelUserTasks");
  for (  Task task : taskService.createTaskQuery().list()) {
    taskService.complete(task.getId());
  }
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricTaskInstance> historicTaskInstances=historyService.createHistoricTaskInstanceQuery().orderByTaskAssignee().asc().list();
    for (int i=0; i < historicTaskInstances.size(); i++) {
      HistoricTaskInstance hi=historicTaskInstances.get(i);
      assertNotNull(hi.getStartTime());
      assertNotNull(hi.getEndTime());
      assertEquals("My Task " + i,hi.getName());
      assertEquals("kermit_" + i,hi.getAssignee());
    }
    List<HistoricActivityInstance> historicActivityInstances=historyService.createHistoricActivityInstanceQuery().activityType("userTask").list();
    assertEquals(3,historicActivityInstances.size());
    for (    HistoricActivityInstance hai : historicActivityInstances) {
      assertNotNull(hai.getStartTime());
      assertNotNull(hai.getEndTime());
      assertNotNull(hai.getAssignee());
      assertEquals("userTask",hai.getActivityType());
    }
  }
}
