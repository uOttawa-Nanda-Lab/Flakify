@Deployment public void testStartProcessInstanceByKey(){
  ProcessInstance processInstance=runtimeService.createProcessInstanceBuilder().processDefinitionKey("transientVarsTest").transientVariable("variable","gotoA").start();
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("A",task.getName());
  assertEquals(0,runtimeService.getVariables(processInstance.getId()).size());
  processInstance=runtimeService.createProcessInstanceBuilder().processDefinitionKey("transientVarsTest").transientVariable("variable","gotoB").start();
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("B",task.getName());
  assertEquals(0,runtimeService.getVariables(processInstance.getId()).size());
  processInstance=runtimeService.createProcessInstanceBuilder().processDefinitionKey("transientVarsTest").transientVariable("variable","somethingElse").start();
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("Default",task.getName());
  assertEquals(0,runtimeService.getVariables(processInstance.getId()).size());
}
