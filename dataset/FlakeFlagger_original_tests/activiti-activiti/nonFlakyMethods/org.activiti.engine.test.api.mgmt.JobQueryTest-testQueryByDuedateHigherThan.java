public void testQueryByDuedateHigherThan(){
  JobQuery query=managementService.createJobQuery().duedateHigherThan(testStartTime);
  verifyQueryResults(query,0);
  query=managementService.createJobQuery();
  verifyQueryResults(query,1);
  TimerJobQuery timerQuery=managementService.createTimerJobQuery().duedateHigherThan(testStartTime);
  verifyQueryResults(timerQuery,3);
  query=managementService.createJobQuery().duedateHigherThan(timerOneFireTime);
  verifyQueryResults(query,0);
  timerQuery=managementService.createTimerJobQuery().duedateHigherThan(timerOneFireTime);
  verifyQueryResults(timerQuery,2);
  timerQuery=managementService.createTimerJobQuery().duedateHigherThan(timerTwoFireTime);
  verifyQueryResults(timerQuery,1);
  timerQuery=managementService.createTimerJobQuery().duedateHigherThan(timerThreeFireTime);
  verifyQueryResults(timerQuery,0);
}
