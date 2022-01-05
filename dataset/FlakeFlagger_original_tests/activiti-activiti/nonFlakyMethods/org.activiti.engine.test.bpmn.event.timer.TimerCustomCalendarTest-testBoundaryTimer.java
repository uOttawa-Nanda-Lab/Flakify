@Deployment public void testBoundaryTimer(){
  this.runtimeService.startProcessInstanceByKey("testBoundaryTimer");
  List<Job> jobs=this.managementService.createTimerJobQuery().list();
  assertThat("One job is scheduled",jobs.size(),is(1));
  assertThat("Job must be scheduled by custom business calendar to Date(0)",jobs.get(0).getDuedate(),is(new Date(0)));
  managementService.moveTimerToExecutableJob(jobs.get(0).getId());
  managementService.executeJob(jobs.get(0).getId());
  waitForJobExecutorToProcessAllJobsAndExecutableTimerJobs(10000,200);
}
