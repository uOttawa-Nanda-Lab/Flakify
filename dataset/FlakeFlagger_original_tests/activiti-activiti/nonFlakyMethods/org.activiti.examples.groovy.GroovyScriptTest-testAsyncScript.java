@Deployment public void testAsyncScript(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testAsyncScript");
  JobQuery jobQuery=managementService.createJobQuery().processInstanceId(processInstance.getId());
  List<Job> jobs=jobQuery.list();
  assertEquals(1,jobs.size());
  waitForJobExecutorToProcessAllJobs(5000L,100L);
  assertEquals(0L,jobQuery.count());
  assertProcessEnded(processInstance.getId());
}
