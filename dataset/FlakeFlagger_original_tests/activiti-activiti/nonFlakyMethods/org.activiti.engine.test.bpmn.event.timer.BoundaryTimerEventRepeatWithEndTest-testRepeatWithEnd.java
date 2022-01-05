@Deployment public void testRepeatWithEnd() throws Throwable {
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
  Job executableJob=managementService.moveTimerToExecutableJob(jobs.get(0).getId());
  managementService.executeJob(executableJob.getId());
  assertEquals(0,managementService.createJobQuery().list().size());
  jobs=managementService.createTimerJobQuery().list();
  assertEquals(1,jobs.size());
  nextTimeCal.add(Calendar.MINUTE,15);
  processEngineConfiguration.getClock().setCurrentTime(nextTimeCal.getTime());
  executableJob=managementService.moveTimerToExecutableJob(jobs.get(0).getId());
  managementService.executeJob(executableJob.getId());
  assertEquals(0,managementService.createJobQuery().list().size());
  jobs=managementService.createTimerJobQuery().list();
  assertEquals(1,jobs.size());
  nextTimeCal.add(Calendar.MINUTE,5);
  nextTimeCal.add(Calendar.SECOND,1);
  processEngineConfiguration.getClock().setCurrentTime(nextTimeCal.getTime());
  executableJob=managementService.moveTimerToExecutableJob(jobs.get(0).getId());
  managementService.executeJob(executableJob.getId());
  jobs=managementService.createTimerJobQuery().list();
  assertEquals(0,jobs.size());
  jobs=managementService.createJobQuery().list();
  assertEquals(0,jobs.size());
  tasks=taskService.createTaskQuery().list();
  task=tasks.get(0);
  assertEquals("Task B",task.getName());
  assertEquals(1,tasks.size());
  taskService.complete(task.getId());
  jobs=managementService.createTimerJobQuery().list();
  assertEquals(0,jobs.size());
  jobs=managementService.createJobQuery().list();
  assertEquals(0,jobs.size());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance historicInstance=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
    assertNotNull(historicInstance.getEndTime());
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