@Deployment public void testRepeatWithoutEnd() throws Throwable {
  Calendar calendar=Calendar.getInstance();
  Date baseTime=calendar.getTime();
  calendar.add(Calendar.MINUTE,20);
  DateTimeFormatter fmt=ISODateTimeFormat.dateTime();
  DateTime dt=new DateTime(calendar.getTime());
  String dateStr=fmt.print(dt);
  Calendar nextTimeCal=Calendar.getInstance();
  nextTimeCal.setTime(baseTime);
  processEngineConfiguration.getClock().setCurrentTime(nextTimeCal.getTime());
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("repeatWithEnd");
  runtimeService.setVariable(processInstance.getId(),"EndDateForBoundary",dateStr);
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(1,tasks.size());
  Task task=tasks.get(0);
  assertEquals("Task A",task.getName());
  taskService.complete(task.getId());
  List<Job> jobs=managementService.createTimerJobQuery().list();
  assertEquals(1,jobs.size());
  TimerJobEntity job=(TimerJobEntity)jobs.get(0);
  changeConfigurationToPlainText(job);
  waitForJobExecutorToProcessAllJobs(2000,100);
  jobs=managementService.createTimerJobQuery().list();
  assertEquals(1,jobs.size());
  for (int i=0; i < 9; i++) {
    nextTimeCal.add(Calendar.SECOND,2);
    processEngineConfiguration.getClock().setCurrentTime(nextTimeCal.getTime());
    waitForJobExecutorToProcessAllJobs(2000,100);
    jobs=managementService.createTimerJobQuery().list();
    assertEquals(1,jobs.size());
  }
  nextTimeCal.add(Calendar.SECOND,2);
  processEngineConfiguration.getClock().setCurrentTime(nextTimeCal.getTime());
  try {
    waitForJobExecutorToProcessAllJobs(2000,100);
  }
 catch (  Exception ex) {
    fail("Should not have any other jobs because the endDate is reached");
  }
  tasks=taskService.createTaskQuery().list();
  task=tasks.get(0);
  assertEquals("Task B",task.getName());
  assertEquals(1,tasks.size());
  taskService.complete(task.getId());
  try {
    waitForJobExecutorToProcessAllJobs(2000,500);
  }
 catch (  Exception e) {
    fail("No jobs should be active here.");
  }
  List<ProcessInstance> processInstances=runtimeService.createProcessInstanceQuery().list();
  assertEquals(0,processInstances.size());
  jobs=managementService.createJobQuery().list();
  assertEquals(0,jobs.size());
  jobs=managementService.createTimerJobQuery().list();
  assertEquals(0,jobs.size());
  tasks=taskService.createTaskQuery().list();
  assertEquals(0,tasks.size());
}
