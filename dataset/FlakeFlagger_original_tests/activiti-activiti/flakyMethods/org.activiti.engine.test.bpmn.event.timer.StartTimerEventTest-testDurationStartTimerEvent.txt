@Deployment public void testDurationStartTimerEvent() throws Exception {
  Date startTime=new Date();
  TimerJobQuery jobQuery=managementService.createTimerJobQuery();
  assertEquals(1,jobQuery.count());
  processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + ((50 * 60 * 1000) + 5000)));
  waitForJobExecutorToProcessAllJobs(5000L,200L);
  List<ProcessInstance> pi=runtimeService.createProcessInstanceQuery().processDefinitionKey("startTimerEventExample").list();
  assertEquals(1,pi.size());
  assertEquals(0,jobQuery.count());
}
