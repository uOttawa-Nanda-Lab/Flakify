public void testQueryByRetriesLeft(){
  JobQuery query=managementService.createJobQuery();
  verifyQueryResults(query,1);
  TimerJobQuery timerQuery=managementService.createTimerJobQuery();
  verifyQueryResults(timerQuery,3);
  final Job job=managementService.createTimerJobQuery().processInstanceId(processInstanceIdOne).singleResult();
  managementService.setTimerJobRetries(job.getId(),0);
  managementService.moveJobToDeadLetterJob(job.getId());
  verifyQueryResults(query,1);
  verifyQueryResults(timerQuery,2);
}
