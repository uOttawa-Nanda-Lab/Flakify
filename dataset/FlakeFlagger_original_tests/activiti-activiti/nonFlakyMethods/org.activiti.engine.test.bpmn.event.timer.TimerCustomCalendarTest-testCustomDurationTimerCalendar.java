@Deployment public void testCustomDurationTimerCalendar(){
  ProcessInstance processInstance=this.runtimeService.startProcessInstanceByKey("testCustomDurationCalendar");
  List<Job> jobs=this.managementService.createTimerJobQuery().list();
  assertThat("One job is scheduled",jobs.size(),is(1));
  assertThat("Job must be scheduled by custom business calendar to Date(0)",jobs.get(0).getDuedate(),is(new Date(0)));
  managementService.moveTimerToExecutableJob(jobs.get(0).getId());
  managementService.executeJob(jobs.get(0).getId());
  waitForJobExecutorToProcessAllJobsAndExecutableTimerJobs(10000,200);
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).activityId("receive").singleResult();
  runtimeService.trigger(execution.getId());
}
