@Deployment public void testSequentialScriptTasks(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("sum",0);
  vars.put(NR_OF_LOOPS_KEY,5);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("miSequentialScriptTask",vars);
  int sum=(Integer)runtimeService.getVariable(processInstance.getId(),"sum");
  assertEquals(10,sum);
}
