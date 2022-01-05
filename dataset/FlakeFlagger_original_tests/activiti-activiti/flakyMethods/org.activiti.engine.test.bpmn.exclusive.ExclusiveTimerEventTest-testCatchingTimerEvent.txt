@Deployment public void testCatchingTimerEvent() throws Exception {
  Date startTime=new Date();
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("exclusiveTimers");
  TimerJobQuery jobQuery=managementService.createTimerJobQuery().processInstanceId(pi.getId());
  assertEquals(3,jobQuery.count());
  processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + ((50 * 60 * 1000) + 5000)));
  waitForJobExecutorToProcessAllJobsAndExecutableTimerJobs(5000L,500L);
  assertEquals(0,jobQuery.count());
  assertProcessEnded(pi.getProcessInstanceId());
}
