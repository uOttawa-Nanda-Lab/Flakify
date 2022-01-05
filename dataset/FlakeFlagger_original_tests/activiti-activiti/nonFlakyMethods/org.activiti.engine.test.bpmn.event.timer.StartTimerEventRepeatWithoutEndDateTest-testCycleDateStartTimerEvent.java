/** 
 * Timer repetition
 */
public void testCycleDateStartTimerEvent() throws Exception {
  Clock previousClock=processEngineConfiguration.getClock();
  Clock testClock=new DefaultClockImpl();
  processEngineConfiguration.setClock(testClock);
  Calendar calendar=Calendar.getInstance();
  calendar.set(2025,Calendar.DECEMBER,10,0,0,0);
  testClock.setCurrentTime(calendar.getTime());
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventRepeatWithoutEndDateTest.testCycleDateStartTimerEvent.bpmn20.xml").deploy();
  assertEquals(1,repositoryService.createProcessDefinitionQuery().count());
  List<Job> jobs=managementService.createTimerJobQuery().list();
  assertEquals(1,jobs.size());
  Calendar dueDateCalendar=Calendar.getInstance();
  dueDateCalendar.set(2025,Calendar.DECEMBER,11,0,0,0);
  assertEquals(true,Math.abs(dueDateCalendar.getTime().getTime() - jobs.get(0).getDuedate().getTime()) < 2000);
  List<ProcessInstance> processInstances=runtimeService.createProcessInstanceQuery().list();
  assertEquals(0,processInstances.size());
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(0,tasks.size());
  moveByMinutes((9 * 60 * 24));
  executeJobExecutorForTime(10000,200);
  assertEquals(1,managementService.createTimerJobQuery().count());
  processInstances=runtimeService.createProcessInstanceQuery().list();
  assertEquals(9,processInstances.size());
  tasks=taskService.createTaskQuery().list();
  assertEquals(9,tasks.size());
  jobs=managementService.createTimerJobQuery().list();
  assertEquals(1,jobs.size());
  dueDateCalendar=Calendar.getInstance();
  dueDateCalendar.set(2025,Calendar.DECEMBER,20,0,0,0);
  assertEquals(true,Math.abs(dueDateCalendar.getTime().getTime() - jobs.get(0).getDuedate().getTime()) < 2000);
  moveByMinutes(60 * 24);
  try {
    waitForJobExecutorToProcessAllJobsAndExecutableTimerJobs(2000,200);
  }
 catch (  Exception e) {
    fail("Because the maximum number of repeats is reached no other jobs are created");
  }
  processInstances=runtimeService.createProcessInstanceQuery().list();
  assertEquals(10,processInstances.size());
  jobs=managementService.createTimerJobQuery().list();
  assertEquals(0,jobs.size());
  jobs=managementService.createJobQuery().list();
  assertEquals(0,jobs.size());
  tasks=taskService.createTaskQuery().list();
  assertEquals(10,tasks.size());
  int timerFiredCount=0;
  List<ActivitiEvent> eventsReceived=listener.getEventsReceived();
  for (  ActivitiEvent eventReceived : eventsReceived) {
    if (ActivitiEventType.TIMER_FIRED.equals(eventReceived.getType())) {
      timerFiredCount++;
    }
  }
  int eventCreatedCount=0;
  for (  ActivitiEvent eventReceived : eventsReceived) {
    if (ActivitiEventType.ENTITY_CREATED.equals(eventReceived.getType())) {
      eventCreatedCount++;
    }
  }
  int eventDeletedCount=0;
  for (  ActivitiEvent eventReceived : eventsReceived) {
    if (ActivitiEventType.ENTITY_DELETED.equals(eventReceived.getType())) {
      eventDeletedCount++;
    }
  }
  assertEquals(10,timerFiredCount);
  assertEquals(20,eventCreatedCount);
  assertEquals(20,eventDeletedCount);
  for (  ProcessInstance processInstance : processInstances) {
    tasks=taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).list();
    Task task=tasks.get(0);
    assertEquals("Task A",task.getName());
    assertEquals(1,tasks.size());
    taskService.complete(task.getId());
  }
  processInstances=runtimeService.createProcessInstanceQuery().list();
  assertEquals(0,processInstances.size());
  jobs=managementService.createTimerJobQuery().list();
  assertEquals(0,jobs.size());
  jobs=managementService.createJobQuery().list();
  assertEquals(0,jobs.size());
  tasks=taskService.createTaskQuery().list();
  assertEquals(0,tasks.size());
  listener.clearEventsReceived();
  processEngineConfiguration.setClock(previousClock);
  repositoryService.deleteDeployment(repositoryService.createDeploymentQuery().singleResult().getId(),true);
}
