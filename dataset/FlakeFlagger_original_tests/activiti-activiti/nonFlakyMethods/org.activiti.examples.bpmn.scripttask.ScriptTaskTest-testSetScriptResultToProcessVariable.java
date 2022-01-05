@Deployment public void testSetScriptResultToProcessVariable(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("echo","hello");
  variables.put("existingProcessVariableName","one");
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("setScriptResultToProcessVariable",variables);
  assertEquals("hello",runtimeService.getVariable(pi.getId(),"existingProcessVariableName"));
  assertEquals(pi.getId(),runtimeService.getVariable(pi.getId(),"newProcessVariableName"));
}
