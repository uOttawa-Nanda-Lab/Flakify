@org.activiti.engine.test.Deployment public void testGetVariableInDelegateMixed3(){
  Map<String,Object> vars=generateVariables();
  vars.put("testVar1","one");
  vars.put("testVar2","two");
  vars.put("testVar3","three");
  String processInstanceId=runtimeService.startProcessInstanceByKey("variablesFetchingTestProcess",vars).getId();
  taskService.complete(taskService.createTaskQuery().taskName("Task A").singleResult().getId());
  taskService.complete(taskService.createTaskQuery().taskName("Task B").singleResult().getId());
  assertEquals("one-CHANGED",(String)runtimeService.getVariable(processInstanceId,"testVar1"));
  assertEquals("two-CHANGED",(String)runtimeService.getVariable(processInstanceId,"testVar2"));
  assertNull(runtimeService.getVariable(processInstanceId,"testVar3"));
}
