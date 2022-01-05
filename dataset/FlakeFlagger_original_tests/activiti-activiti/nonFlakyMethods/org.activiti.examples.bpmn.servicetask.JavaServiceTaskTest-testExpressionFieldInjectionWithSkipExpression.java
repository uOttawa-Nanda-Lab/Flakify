@Deployment public void testExpressionFieldInjectionWithSkipExpression(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("name","kermit");
  vars.put("gender","male");
  vars.put("genderBean",new GenderBean());
  vars.put("_ACTIVITI_SKIP_EXPRESSION_ENABLED",true);
  vars.put("skip",false);
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("expressionFieldInjectionWithSkipExpression",vars);
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(pi.getId()).activityId("waitState").singleResult();
  assertEquals("timrek .rM olleH",runtimeService.getVariable(execution.getId(),"var2"));
  assertEquals("elam :si redneg ruoY",runtimeService.getVariable(execution.getId(),"var1"));
  Map<String,Object> vars2=new HashMap<String,Object>();
  vars2.put("name","kermit");
  vars2.put("gender","male");
  vars2.put("genderBean",new GenderBean());
  vars2.put("_ACTIVITI_SKIP_EXPRESSION_ENABLED",true);
  vars2.put("skip",true);
  ProcessInstance pi2=runtimeService.startProcessInstanceByKey("expressionFieldInjectionWithSkipExpression",vars2);
  Execution execution2=runtimeService.createExecutionQuery().processInstanceId(pi2.getId()).activityId("waitState").singleResult();
  assertEquals(null,execution2);
}
