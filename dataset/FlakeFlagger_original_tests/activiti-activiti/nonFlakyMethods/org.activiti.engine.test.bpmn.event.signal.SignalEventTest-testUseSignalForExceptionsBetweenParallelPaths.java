@Deployment public void testUseSignalForExceptionsBetweenParallelPaths(){
  runtimeService.startProcessInstanceByKey("processWithSignal");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Enter developers",task.getName());
  taskService.complete(task.getId(),CollectionUtil.singletonMap("developers",Arrays.asList("developerOne","developerTwo","developerThree")));
  assertEquals("Develop specifications",taskService.createTaskQuery().taskAssignee("developerOne").singleResult().getName());
  assertEquals("Develop specifications",taskService.createTaskQuery().taskAssignee("developerTwo").singleResult().getName());
  assertEquals("Develop specifications",taskService.createTaskQuery().taskAssignee("developerThree").singleResult().getName());
  task=taskService.createTaskQuery().taskAssignee("kermit").singleResult();
  assertEquals("Negotiate with client",task.getName());
  taskService.complete(task.getId(),CollectionUtil.singletonMap("negotationFailed",true));
  assertEquals(0,taskService.createTaskQuery().count());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
