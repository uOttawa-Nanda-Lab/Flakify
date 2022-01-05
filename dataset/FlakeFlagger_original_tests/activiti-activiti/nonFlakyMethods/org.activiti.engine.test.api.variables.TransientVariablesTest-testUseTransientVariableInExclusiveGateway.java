@Deployment public void testUseTransientVariableInExclusiveGateway(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transientVarsTest");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("responseOk",task.getTaskDefinitionKey());
  assertNull(runtimeService.getVariable(processInstance.getId(),"response"));
}
