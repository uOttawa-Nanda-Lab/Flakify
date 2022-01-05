@org.activiti.engine.test.Deployment public void testGetVariableAllVariableFetchingDefault(){
  Map<String,Object> vars=generateVariables();
  vars.put("testVar","hello");
  String processInstanceId=runtimeService.startProcessInstanceByKey("variablesFetchingTestProcess",vars).getId();
  taskService.complete(taskService.createTaskQuery().taskName("Task A").singleResult().getId());
  taskService.complete(taskService.createTaskQuery().taskName("Task B").singleResult().getId());
  vars=runtimeService.getVariables(processInstanceId);
  assertEquals(71,vars.size());
  String varValue=(String)runtimeService.getVariable(processInstanceId,"testVar");
  assertEquals("HELLO world",varValue);
}
