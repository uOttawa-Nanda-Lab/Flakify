@Deployment public void testInfiniteRepeatingTimer() throws Exception {
  SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyy.MM.dd hh:mm");
  Date currentTime=simpleDateFormat.parse("2015.10.01 11:01");
  processEngineConfiguration.getClock().setCurrentTime(currentTime);
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("timerString","R/2015-10-01T11:00:00/PT24H");
  runtimeService.startProcessInstanceByKey("testTimerErrors",vars);
  long twentyFourHours=24L * 60L * 60L* 1000L;
  Date previousDueDate=null;
  for (int i=0; i < 30; i++) {
    Job job=managementService.createTimerJobQuery().singleResult();
    if (previousDueDate != null) {
      assertTrue(job.getDuedate().getTime() - previousDueDate.getTime() >= twentyFourHours);
    }
    previousDueDate=job.getDuedate();
    currentTime=new Date(currentTime.getTime() + twentyFourHours + (60 * 1000));
    processEngineConfiguration.getClock().setCurrentTime(currentTime);
    String jobId=managementService.createTimerJobQuery().singleResult().getId();
    managementService.moveTimerToExecutableJob(jobId);
    managementService.executeJob(jobId);
  }
}
