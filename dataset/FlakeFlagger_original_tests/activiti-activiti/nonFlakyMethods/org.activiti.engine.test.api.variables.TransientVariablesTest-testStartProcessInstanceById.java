@Deployment public void testStartProcessInstanceById(){
  String processDefinitionId=repositoryService.createProcessDefinitionQuery().singleResult().getId();
  ProcessInstance processInstance=runtimeService.createProcessInstanceBuilder().processDefinitionId(processDefinitionId).transientVariable("variable","gotoA").start();
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("A",task.getName());
  assertEquals(0,runtimeService.getVariables(processInstance.getId()).size());
  processInstance=runtimeService.createProcessInstanceBuilder().processDefinitionId(processDefinitionId).transientVariable("variable","gotoB").start();
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("B",task.getName());
  assertEquals(0,runtimeService.getVariables(processInstance.getId()).size());
  processInstance=runtimeService.createProcessInstanceBuilder().processDefinitionId(processDefinitionId).transientVariable("variable","somethingElse").start();
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("Default",task.getName());
  assertEquals(0,runtimeService.getVariables(processInstance.getId()).size());
}
