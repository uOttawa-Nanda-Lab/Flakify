@org.activiti.engine.test.Deployment public void testGetVariableInDelegateMixed(){
  Map<String,Object> vars=generateVariables();
  String processInstanceId=runtimeService.startProcessInstanceByKey("variablesFetchingTestProcess",vars).getId();
  taskService.complete(taskService.createTaskQuery().taskName("Task A").singleResult().getId());
  taskService.complete(taskService.createTaskQuery().taskName("Task B").singleResult().getId());
  assertEquals("test 1 2 3",(String)runtimeService.getVariable(processInstanceId,"testVar"));
  assertEquals("Hiya",(String)runtimeService.getVariable(processInstanceId,"testVar2"));
}
