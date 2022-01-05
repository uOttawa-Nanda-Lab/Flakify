@Deployment public void testAsyncServiceListeners(){
  String pid=runtimeService.startProcessInstanceByKey("asyncService").getProcessInstanceId();
  assertEquals(1,managementService.createJobQuery().count());
  assertNull(runtimeService.getVariable(pid,"listener"));
  waitForJobExecutorToProcessAllJobs(5000L,100L);
  assertEquals(0,managementService.createJobQuery().count());
}
