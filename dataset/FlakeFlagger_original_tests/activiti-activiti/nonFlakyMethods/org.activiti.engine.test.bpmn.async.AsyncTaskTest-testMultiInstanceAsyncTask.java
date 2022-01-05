@Deployment public void testMultiInstanceAsyncTask(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("asyncTask");
  assertEquals(3,managementService.createJobQuery().processInstanceId(processInstance.getId()).count());
  managementService.executeJob(managementService.createJobQuery().processInstanceId(processInstance.getId()).list().get(0).getId());
  assertEquals(2,managementService.createJobQuery().processInstanceId(processInstance.getId()).count());
  managementService.executeJob(managementService.createJobQuery().processInstanceId(processInstance.getId()).list().get(0).getId());
  assertEquals(1,managementService.createJobQuery().processInstanceId(processInstance.getId()).count());
  managementService.executeJob(managementService.createJobQuery().processInstanceId(processInstance.getId()).singleResult().getId());
  assertEquals(0,managementService.createJobQuery().processInstanceId(processInstance.getId()).count());
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
