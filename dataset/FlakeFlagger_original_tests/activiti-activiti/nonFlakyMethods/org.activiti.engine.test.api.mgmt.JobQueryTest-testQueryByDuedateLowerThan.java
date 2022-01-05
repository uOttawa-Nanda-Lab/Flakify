public void testQueryByDuedateLowerThan(){
  JobQuery query=managementService.createJobQuery().duedateLowerThan(testStartTime);
  verifyQueryResults(query,0);
  TimerJobQuery timerQuery=managementService.createTimerJobQuery().duedateLowerThan(testStartTime);
  verifyQueryResults(timerQuery,0);
  timerQuery=managementService.createTimerJobQuery().duedateLowerThan(new Date(timerOneFireTime.getTime() + ONE_SECOND));
  verifyQueryResults(timerQuery,1);
  timerQuery=managementService.createTimerJobQuery().duedateLowerThan(new Date(timerTwoFireTime.getTime() + ONE_SECOND));
  verifyQueryResults(timerQuery,2);
  timerQuery=managementService.createTimerJobQuery().duedateLowerThan(new Date(timerThreeFireTime.getTime() + ONE_SECOND));
  verifyQueryResults(timerQuery,3);
}
