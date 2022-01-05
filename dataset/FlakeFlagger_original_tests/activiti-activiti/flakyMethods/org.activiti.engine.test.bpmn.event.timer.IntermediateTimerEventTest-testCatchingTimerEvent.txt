@Deployment public void testCatchingTimerEvent() throws Exception {
  Date startTime=new Date();
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("intermediateTimerEventExample");
  TimerJobQuery jobQuery=managementService.createTimerJobQuery().processInstanceId(pi.getId());
  assertEquals(1,jobQuery.count());
  processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + ((50 * 60 * 1000) + 5000)));
  waitForJobExecutorToProcessAllJobs(5000L,25L);
  assertEquals(0,jobQuery.count());
  assertProcessEnded(pi.getProcessInstanceId());
}
