@Deployment public void testExclusiveService(){
  runtimeService.startProcessInstanceByKey("exclusive");
  Job job=managementService.createJobQuery().singleResult();
  assertNotNull(job);
  assertTrue(((JobEntity)job).isExclusive());
  waitForJobExecutorToProcessAllJobs(6000L,100L);
  assertEquals(0,managementService.createJobQuery().count());
}
