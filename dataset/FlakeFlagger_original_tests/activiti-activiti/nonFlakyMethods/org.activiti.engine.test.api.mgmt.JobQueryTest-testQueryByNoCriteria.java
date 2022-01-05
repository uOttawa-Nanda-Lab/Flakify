public void testQueryByNoCriteria(){
  JobQuery query=managementService.createJobQuery();
  verifyQueryResults(query,1);
  TimerJobQuery timerQuery=managementService.createTimerJobQuery();
  verifyQueryResults(timerQuery,3);
}
