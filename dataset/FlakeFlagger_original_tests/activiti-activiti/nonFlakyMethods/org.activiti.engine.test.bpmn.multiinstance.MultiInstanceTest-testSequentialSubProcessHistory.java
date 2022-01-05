@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testSequentialSubProcess.bpmn20.xml"}) public void testSequentialSubProcessHistory(){
  runtimeService.startProcessInstanceByKey("miSequentialSubprocess");
  for (int i=0; i < 4; i++) {
    List<Task> tasks=taskService.createTaskQuery().list();
    taskService.complete(tasks.get(0).getId());
    taskService.complete(tasks.get(1).getId());
  }
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricActivityInstance> onlySubProcessInstances=historyService.createHistoricActivityInstanceQuery().activityType("subProcess").list();
    assertEquals(4,onlySubProcessInstances.size());
    List<HistoricActivityInstance> historicInstances=historyService.createHistoricActivityInstanceQuery().activityType("subProcess").list();
    assertEquals(4,historicInstances.size());
    for (    HistoricActivityInstance hai : historicInstances) {
      assertNotNull(hai.getStartTime());
      assertNotNull(hai.getEndTime());
    }
    historicInstances=historyService.createHistoricActivityInstanceQuery().activityType("userTask").list();
    assertEquals(8,historicInstances.size());
    for (    HistoricActivityInstance hai : historicInstances) {
      assertNotNull(hai.getStartTime());
      assertNotNull(hai.getEndTime());
    }
  }
}
