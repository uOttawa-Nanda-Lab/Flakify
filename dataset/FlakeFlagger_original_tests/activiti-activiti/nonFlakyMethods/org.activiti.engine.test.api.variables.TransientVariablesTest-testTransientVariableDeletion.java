@Deployment public void testTransientVariableDeletion(){
  String processInstanceId=runtimeService.startProcessInstanceByKey("transientVarsTest").getId();
  long count=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).variableName("response").count();
  assertEquals(0,count);
  count=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).variableName("status").count();
  assertEquals(0,count);
}
