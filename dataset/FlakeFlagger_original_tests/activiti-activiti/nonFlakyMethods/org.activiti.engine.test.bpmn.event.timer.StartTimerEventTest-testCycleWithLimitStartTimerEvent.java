@Deployment public void testCycleWithLimitStartTimerEvent() throws Exception {
  processEngineConfiguration.getClock().setCurrentTime(new Date());
  TimerJobQuery jobQuery=managementService.createTimerJobQuery();
  assertEquals(1,jobQuery.count());
  moveByMinutes(6);
  String jobId=managementService.createTimerJobQuery().executable().singleResult().getId();
  managementService.moveTimerToExecutableJob(jobId);
  managementService.executeJob(jobId);
  assertEquals(1,jobQuery.count());
  moveByMinutes(6);
  jobId=managementService.createTimerJobQuery().executable().singleResult().getId();
  managementService.moveTimerToExecutableJob(jobId);
  managementService.executeJob(jobId);
  assertEquals(0,jobQuery.count());
}
