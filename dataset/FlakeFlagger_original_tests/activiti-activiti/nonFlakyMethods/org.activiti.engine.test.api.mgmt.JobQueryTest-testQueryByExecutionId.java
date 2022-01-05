public void testQueryByExecutionId(){
  Job job=managementService.createTimerJobQuery().processInstanceId(processInstanceIdOne).singleResult();
  TimerJobQuery query=managementService.createTimerJobQuery().executionId(job.getExecutionId());
  assertEquals(query.singleResult().getId(),job.getId());
  verifyQueryResults(query,1);
}
