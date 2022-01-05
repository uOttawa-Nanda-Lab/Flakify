/** 
 * Test for https://activiti.atlassian.net/browse/ACT-4035.
 */
@Deployment public void testExclusiveJobs(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    runtimeService.startProcessInstanceByKey("testExclusiveJobs");
    waitForJobExecutorToProcessAllJobs(20000L,500L);
    HistoricActivityInstance scriptTaskAInstance=historyService.createHistoricActivityInstanceQuery().activityId("scriptTaskA").singleResult();
    HistoricActivityInstance scriptTaskBInstance=historyService.createHistoricActivityInstanceQuery().activityId("scriptTaskB").singleResult();
    long endTimeA=scriptTaskAInstance.getEndTime().getTime();
    long endTimeB=scriptTaskBInstance.getEndTime().getTime();
    long endTimeDifference=0;
    if (endTimeB > endTimeA) {
      endTimeDifference=endTimeB - endTimeA;
    }
 else {
      endTimeDifference=endTimeA - endTimeB;
    }
    assertTrue(endTimeDifference > 6000);
  }
}
