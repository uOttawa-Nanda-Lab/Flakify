@Deployment public void testExpressionOnTimer(){
  Date startTime=new Date();
  HashMap<String,Object> variables=new HashMap<String,Object>();
  variables.put("duration","PT1H");
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("testExpressionOnTimer",variables);
  TimerJobQuery jobQuery=managementService.createTimerJobQuery().processInstanceId(pi.getId());
  List<Job> jobs=jobQuery.list();
  assertEquals(1,jobs.size());
  processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + ((60 * 60 * 1000) + 5000)));
  waitForJobExecutorToProcessAllJobs(5000L,25L);
  assertEquals(0L,jobQuery.count());
  assertFalse(listenerExecutedStartEvent);
  assertTrue(listenerExecutedEndEvent);
  assertProcessEnded(pi.getId());
}
