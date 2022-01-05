@Deployment public void testNoneEndEventAfterSignalInConcurrentProcess(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("my-process");
  assertNotNull(processInstance);
  Task task=taskService.createTaskQuery().taskDefinitionKey("usertask1").singleResult();
  taskService.claim(task.getId(),"user");
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().singleResult();
  assertEquals("usertask2",task.getTaskDefinitionKey());
}
