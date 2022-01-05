@Deployment public void testMethodExpressions(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("aString","abcdefgh");
  runtimeService.startProcessInstanceByKey("methodExpressionProcess",vars);
  assertEquals(0,runtimeService.createProcessInstanceQuery().processDefinitionKey("methodExpressionProcess").count());
}
