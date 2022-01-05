@Deployment public void testResetExpiredJobs(){
  Date startOfTestTime=new Date();
  processEngineConfiguration.getClock().setCurrentTime(startOfTestTime);
  runtimeService.startProcessInstanceByKey("myProcess");
  assertEquals(1,managementService.createJobQuery().count());
  int expiredJobsPagesSize=processEngineConfiguration.getAsyncExecutorResetExpiredJobsPageSize();
  List<JobEntity> expiredJobs=managementService.executeCommand(new FindExpiredJobsCmd(expiredJobsPagesSize));
  assertEquals(0,expiredJobs.size());
  assertJobDetails(false);
  managementService.executeCommand(new AcquireJobsCmd(processEngineConfiguration.getAsyncExecutor()));
  assertJobDetails(true);
  expiredJobs=managementService.executeCommand(new FindExpiredJobsCmd(expiredJobsPagesSize));
  assertEquals(0,expiredJobs.size());
  assertJobDetails(true);
  Date newDate=new Date(startOfTestTime.getTime() + processEngineConfiguration.getAsyncExecutor().getAsyncJobLockTimeInMillis() + 10000);
  processEngineConfiguration.getClock().setCurrentTime(newDate);
  expiredJobs=managementService.executeCommand(new FindExpiredJobsCmd(expiredJobsPagesSize));
  assertTrue(expiredJobs.size() > 0);
  List<String> jobIds=new ArrayList<String>();
  for (  JobEntity jobEntity : expiredJobs) {
    jobIds.add(jobEntity.getId());
  }
  managementService.executeCommand(new ResetExpiredJobsCmd(jobIds));
  assertJobDetails(false);
  managementService.executeCommand(new AcquireJobsCmd(processEngineConfiguration.getAsyncExecutor()));
  assertJobDetails(true);
  runtimeService.startProcessInstanceByKey("myProcess");
  runtimeService.startProcessInstanceByKey("myProcess");
  assertEquals(3,managementService.createJobQuery().count());
  assertJobDetails(true);
  List<Job> unlockedJobs=managementService.createJobQuery().unlocked().list();
  assertEquals(2,unlockedJobs.size());
  for (  Job job : unlockedJobs) {
    JobEntity jobEntity=(JobEntity)job;
    assertNull(jobEntity.getLockOwner());
    assertNull(jobEntity.getLockExpirationTime());
  }
}
