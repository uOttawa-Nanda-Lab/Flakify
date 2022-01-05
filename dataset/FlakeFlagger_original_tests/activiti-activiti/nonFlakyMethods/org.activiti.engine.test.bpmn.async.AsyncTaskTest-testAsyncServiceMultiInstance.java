@Deployment public void testAsyncServiceMultiInstance(){
  INVOCATION=false;
  runtimeService.startProcessInstanceByKey("asyncService");
  assertEquals(1,managementService.createJobQuery().count());
  assertFalse(INVOCATION);
  waitForJobExecutorToProcessAllJobs(5000L,100L);
  assertTrue(INVOCATION);
  assertEquals(0,managementService.createJobQuery().count());
}
