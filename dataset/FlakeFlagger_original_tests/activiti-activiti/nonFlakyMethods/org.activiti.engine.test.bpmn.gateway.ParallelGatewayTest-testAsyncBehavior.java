@Deployment public void testAsyncBehavior(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("async");
  waitForJobExecutorToProcessAllJobs(5000L,250L);
  assertEquals(0,runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).count());
}
