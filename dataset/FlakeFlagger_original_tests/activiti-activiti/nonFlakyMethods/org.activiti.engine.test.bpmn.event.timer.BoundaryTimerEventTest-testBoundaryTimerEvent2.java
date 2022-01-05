@Deployment public void testBoundaryTimerEvent2() throws Exception {
  SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyy.MM.dd hh:mm");
  Date currentTime=simpleDateFormat.parse("2015.10.01 11:01");
  processEngineConfiguration.getClock().setCurrentTime(currentTime);
  runtimeService.startProcessInstanceByKey("timerprocess");
  try {
    waitForJobExecutorToProcessAllJobsAndExecutableTimerJobs(2000,200);
  }
 catch (  Exception ex) {
  }
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(1,tasks.size());
  assertEquals("Start",tasks.get(0).getName());
  List<Job> jobList=managementService.createTimerJobQuery().list();
  assertEquals(1,jobList.size());
  long tenMinutes=2L * 60L * 1000L;
  currentTime=new Date(currentTime.getTime() + tenMinutes);
  processEngineConfiguration.getClock().setCurrentTime(currentTime);
  try {
    waitForJobExecutorToProcessAllJobsAndExecutableTimerJobs(2000,200);
  }
 catch (  Exception ex) {
    ex.getCause();
  }
  tasks=taskService.createTaskQuery().list();
  assertEquals(0,tasks.size());
  jobList=managementService.createJobQuery().list();
  assertEquals(0,jobList.size());
  jobList=managementService.createTimerJobQuery().list();
  assertEquals(0,jobList.size());
}
