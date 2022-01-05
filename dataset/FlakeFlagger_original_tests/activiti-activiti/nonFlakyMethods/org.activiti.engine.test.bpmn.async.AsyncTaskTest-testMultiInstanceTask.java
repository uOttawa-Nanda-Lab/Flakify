@Deployment public void testMultiInstanceTask(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("asyncTask");
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricActivityInstance> historicActivities=historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).list();
    int startCount=0;
    int taskCount=0;
    int endCount=0;
    for (    HistoricActivityInstance historicActivityInstance : historicActivities) {
      if ("task".equals(historicActivityInstance.getActivityId())) {
        taskCount++;
      }
 else       if ("theStart".equals(historicActivityInstance.getActivityId())) {
        startCount++;
      }
 else       if ("theEnd".equals(historicActivityInstance.getActivityId())) {
        endCount++;
      }
 else {
        Assert.fail("Unexpected activity found " + historicActivityInstance.getActivityId());
      }
    }
    assertEquals(1,startCount);
    assertEquals(3,taskCount);
    assertEquals(1,endCount);
  }
}
