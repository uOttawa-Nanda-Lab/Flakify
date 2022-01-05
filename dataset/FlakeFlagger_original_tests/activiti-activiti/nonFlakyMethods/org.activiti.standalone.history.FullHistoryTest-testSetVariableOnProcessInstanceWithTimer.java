@Deployment public void testSetVariableOnProcessInstanceWithTimer(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("timerVariablesProcess");
  runtimeService.setVariable(processInstance.getId(),"myVar",123456L);
  assertEquals(123456L,runtimeService.getVariable(processInstance.getId(),"myVar"));
}
