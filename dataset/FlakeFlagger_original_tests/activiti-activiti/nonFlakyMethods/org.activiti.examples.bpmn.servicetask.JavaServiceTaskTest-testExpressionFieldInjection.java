@Deployment public void testExpressionFieldInjection(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("name","kermit");
  vars.put("gender","male");
  vars.put("genderBean",new GenderBean());
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("expressionFieldInjection",vars);
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(pi.getId()).activityId("waitState").singleResult();
  assertEquals("timrek .rM olleH",runtimeService.getVariable(execution.getId(),"var2"));
  assertEquals("elam :si redneg ruoY",runtimeService.getVariable(execution.getId(),"var1"));
}
