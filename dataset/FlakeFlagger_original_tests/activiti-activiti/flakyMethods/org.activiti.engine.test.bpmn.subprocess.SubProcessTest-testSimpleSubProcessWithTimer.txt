@Deployment public void testSimpleSubProcessWithTimer(){
  Date startTime=new Date();
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("simpleSubProcess");
  Task subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Task in subprocess",subProcessTask.getName());
  processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + (2 * 60 * 60* 1000) + 1000));
  assertEquals(1,managementService.createTimerJobQuery().count());
  waitForJobExecutorToProcessAllJobs(5000L,500L);
  Task escalationTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Fix escalated problem",escalationTask.getName());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricTaskInstance historicTaskInstance=historyService.createHistoricTaskInstanceQuery().taskName("Task in subprocess").singleResult();
    assertNotNull(historicTaskInstance.getEndTime());
    HistoricActivityInstance historicActivityInstance=historyService.createHistoricActivityInstanceQuery().activityId("subProcessTask").singleResult();
    assertNotNull(historicActivityInstance.getEndTime());
  }
}
