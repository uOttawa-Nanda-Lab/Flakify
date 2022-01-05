@Deployment public void testRepeatTimerDuration() throws Exception {
  SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyy.MM.dd hh:mm");
  Date currentTime=simpleDateFormat.parse("2015.10.01 11:01");
  processEngineConfiguration.getClock().setCurrentTime(currentTime);
  runtimeService.startProcessInstanceByKey("repeattimertest");
  long twentyFourHours=24L * 60L * 60L* 1000L;
  Date previousDueDate=null;
  for (int i=0; i < 3; i++) {
    Job job=managementService.createTimerJobQuery().singleResult();
    if (previousDueDate != null) {
      assertTrue(job.getDuedate().getTime() - previousDueDate.getTime() >= twentyFourHours);
    }
    previousDueDate=job.getDuedate();
    currentTime=new Date(currentTime.getTime() + twentyFourHours + (60 * 1000));
    processEngineConfiguration.getClock().setCurrentTime(currentTime);
    managementService.moveTimerToExecutableJob(job.getId());
    managementService.executeJob(job.getId());
  }
}
