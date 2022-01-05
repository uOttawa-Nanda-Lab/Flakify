@Deployment public void testSetServiceResultToProcessVariables(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("okReturningService",new OkReturningService());
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("setServiceResultToProcessVariables",variables);
  assertEquals("ok",runtimeService.getVariable(pi.getId(),"result"));
}
