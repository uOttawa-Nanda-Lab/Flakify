@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testParallelSubProcess.bpmn20.xml"}) public void testParallelSubProcessHistory(){
  runtimeService.startProcessInstanceByKey("miParallelSubprocess");
  for (  Task task : taskService.createTaskQuery().list()) {
    taskService.complete(task.getId());
  }
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricActivityInstance> historicActivityInstances=historyService.createHistoricActivityInstanceQuery().activityId("miSubProcess").list();
    assertEquals(2,historicActivityInstances.size());
    for (    HistoricActivityInstance hai : historicActivityInstances) {
      assertNotNull(hai.getStartTime());
      assertNotNull(hai.getEndTime());
    }
  }
}
