@Deployment public void testAsyncUserTask(){
  String pid=runtimeService.startProcessInstanceByKey("asyncUserTask").getId();
  assertEquals(1,managementService.createJobQuery().count());
  assertNull(runtimeService.getVariable(pid,"listener"));
  assertNull(runtimeService.getVariable(pid,"taskListener"));
  assertNull(taskService.createTaskQuery().singleResult());
  waitForJobExecutorToProcessAllJobs(5000L,250L);
  assertNotNull(runtimeService.getVariable(pid,"listener"));
  assertNotNull(runtimeService.getVariable(pid,"taskListener"));
  assertNotNull(taskService.createTaskQuery().singleResult());
  assertEquals(0,managementService.createJobQuery().count());
  String taskId=taskService.createTaskQuery().singleResult().getId();
  taskService.complete(taskId);
}
