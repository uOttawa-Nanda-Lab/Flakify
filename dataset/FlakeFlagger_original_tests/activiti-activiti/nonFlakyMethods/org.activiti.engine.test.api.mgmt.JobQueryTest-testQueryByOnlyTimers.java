public void testQueryByOnlyTimers(){
  JobQuery query=managementService.createJobQuery().timers();
  verifyQueryResults(query,0);
  TimerJobQuery timerQuery=managementService.createTimerJobQuery().timers();
  verifyQueryResults(timerQuery,3);
}
