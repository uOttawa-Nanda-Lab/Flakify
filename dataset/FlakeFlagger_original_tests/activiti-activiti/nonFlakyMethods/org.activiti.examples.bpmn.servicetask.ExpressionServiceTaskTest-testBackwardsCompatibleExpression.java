@Deployment public void testBackwardsCompatibleExpression(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("var","---");
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("BackwardsCompatibleExpressionProcess",variables);
  assertEquals("...---...",runtimeService.getVariable(pi.getId(),"result"));
}
