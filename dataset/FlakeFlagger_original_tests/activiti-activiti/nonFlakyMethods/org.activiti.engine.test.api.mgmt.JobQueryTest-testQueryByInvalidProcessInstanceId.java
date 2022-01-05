public void testQueryByInvalidProcessInstanceId(){
  TimerJobQuery query=managementService.createTimerJobQuery().processInstanceId("invalid");
  verifyQueryResults(query,0);
  try {
    managementService.createJobQuery().processInstanceId(null);
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
