@Deployment public void testFieldInjection(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("fieldInjection");
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(pi.getId()).activityId("waitState").singleResult();
  assertEquals("HELLO WORLD",runtimeService.getVariable(execution.getId(),"var"));
  assertEquals("HELLO SETTER",runtimeService.getVariable(execution.getId(),"setterVar"));
}
