@org.activiti.engine.test.Deployment public void testGetVariableInDelegateMixed2(){
  Map<String,Object> vars=generateVariables();
  vars.put("testVar","1");
  String processInstanceId=runtimeService.startProcessInstanceByKey("variablesFetchingTestProcess",vars).getId();
  taskService.complete(taskService.createTaskQuery().taskName("Task A").singleResult().getId());
  taskService.complete(taskService.createTaskQuery().taskName("Task B").singleResult().getId());
  assertEquals("1234",(String)runtimeService.getVariable(processInstanceId,"testVar"));
}
