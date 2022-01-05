public void testQueryByExecutable(){
  processEngineConfiguration.getClock().setCurrentTime(new Date(timerThreeFireTime.getTime() + ONE_SECOND));
  JobQuery query=managementService.createJobQuery();
  verifyQueryResults(query,1);
  TimerJobQuery timerQuery=managementService.createTimerJobQuery().executable();
  verifyQueryResults(timerQuery,3);
  final Job job=managementService.createTimerJobQuery().processInstanceId(processInstanceIdOne).singleResult();
  managementService.setTimerJobRetries(job.getId(),0);
  managementService.moveJobToDeadLetterJob(job.getId());
  verifyQueryResults(query,1);
  verifyQueryResults(timerQuery,2);
  processEngineConfiguration.getClock().setCurrentTime(testStartTime);
  verifyQueryResults(query,1);
  verifyQueryResults(timerQuery,0);
  managementService.moveDeadLetterJobToExecutableJob(job.getId(),5);
  verifyQueryResults(query,2);
  verifyQueryResults(timerQuery,0);
}
