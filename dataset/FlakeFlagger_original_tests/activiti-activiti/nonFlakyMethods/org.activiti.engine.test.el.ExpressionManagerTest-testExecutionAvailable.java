@Deployment public void testExecutionAvailable(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("myVar",new ExecutionTestVariable());
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testExecutionAvailableProcess",vars);
  String value=(String)runtimeService.getVariable(processInstance.getId(),"testVar");
  assertNotNull(value);
  assertEquals("myValue",value);
}
