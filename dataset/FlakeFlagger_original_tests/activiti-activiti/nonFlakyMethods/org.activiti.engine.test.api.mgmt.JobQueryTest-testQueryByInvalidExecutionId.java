public void testQueryByInvalidExecutionId(){
  JobQuery query=managementService.createJobQuery().executionId("invalid");
  verifyQueryResults(query,0);
  TimerJobQuery timerQuery=managementService.createTimerJobQuery().executionId("invalid");
  verifyQueryResults(timerQuery,0);
  try {
    managementService.createJobQuery().executionId(null).list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    managementService.createTimerJobQuery().executionId(null).list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
