@Deployment public void testSetServiceResultToProcessVariables(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("bean",new ValueBean("ok"));
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("setServiceResultToProcessVariables",variables);
  assertEquals("ok",runtimeService.getVariable(pi.getId(),"result"));
}
