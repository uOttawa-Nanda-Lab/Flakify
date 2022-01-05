@Deployment public void testSetVariableThroughExecutionInScript(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("setScriptVariableThroughExecution");
  assertNull(runtimeService.getVariable(pi.getId(),"scriptVar"));
  assertEquals("test123",runtimeService.getVariable(pi.getId(),"myVar"));
}
