@Deployment public void testFixedDateStartTimerEvent() throws Exception {
  TimerJobQuery jobQuery=managementService.createTimerJobQuery();
  assertEquals(1,jobQuery.count());
  processEngineConfiguration.getClock().setCurrentTime(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse("15/11/2036 11:12:30"));
  waitForJobExecutorToProcessAllJobs(5000L,200L);
  List<ProcessInstance> pi=runtimeService.createProcessInstanceQuery().processDefinitionKey("startTimerEventExample").list();
  assertEquals(1,pi.size());
  assertEquals(0,jobQuery.count());
}
