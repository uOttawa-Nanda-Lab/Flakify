public void testQueryByProcessInstanceId(){
  TimerJobQuery query=managementService.createTimerJobQuery().processInstanceId(processInstanceIdOne);
  verifyQueryResults(query,1);
}
