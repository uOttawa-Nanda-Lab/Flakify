@Deployment public void testSetAuthenticatedUserAndCompleteLastTask(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("identityLinktest");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  Authentication.setAuthenticatedUserId("kermit");
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  Authentication.setAuthenticatedUserId(null);
  assertProcessEnded(processInstance.getId());
}
