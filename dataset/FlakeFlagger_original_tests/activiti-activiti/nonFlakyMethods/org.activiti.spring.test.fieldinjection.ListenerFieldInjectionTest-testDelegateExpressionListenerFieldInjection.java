@Deployment public void testDelegateExpressionListenerFieldInjection(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("listenerFieldInjection",CollectionUtil.singletonMap("startValue",42));
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  Map<String,Object> variables=runtimeService.getVariables(processInstance.getId());
  assertEquals(2,variables.size());
  assertEquals(4200,((Number)variables.get("processStartValue")).intValue());
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  variables=runtimeService.getVariables(processInstance.getId());
  assertEquals(3,variables.size());
  assertEquals(420000,((Number)variables.get("sequenceFlowValue")).intValue());
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  variables=runtimeService.getVariables(processInstance.getId());
  assertEquals(4,variables.size());
  assertEquals(210000,((Number)variables.get("taskCreateValue")).intValue());
  taskService.complete(task.getId());
  variables=runtimeService.getVariables(processInstance.getId());
  assertEquals(5,variables.size());
  assertEquals(105000,((Number)variables.get("taskCompleteValue")).intValue());
  assertEquals(1,TestExecutionListener.INSTANCE_COUNT.get());
  assertEquals(1,TestTaskListener.INSTANCE_COUNT.get());
}
