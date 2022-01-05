@Deployment public void testThrowErrorWithErrorcodeDefinedInProcessDefinition() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testError");
  assertNotNull(processInstance);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("userTask").singleResult();
  assertNotNull(task);
  taskService.setAssignee(task.getId(),"kermit");
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("escalatedTask").singleResult();
  assertNotNull(task);
}
