@Deployment public void testExecutionAndTaskListenerDelegationExpression(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("executionAndTaskListenerDelegation");
  assertEquals("working",runtimeService.getVariable(processInstance.getId(),"executionListenerVar"));
  assertEquals("working",runtimeService.getVariable(processInstance.getId(),"taskListenerVar"));
  assertEquals("executionListenerInjection",runtimeService.getVariable(processInstance.getId(),"executionListenerField"));
  assertEquals("taskListenerInjection",runtimeService.getVariable(processInstance.getId(),"taskListenerField"));
}
