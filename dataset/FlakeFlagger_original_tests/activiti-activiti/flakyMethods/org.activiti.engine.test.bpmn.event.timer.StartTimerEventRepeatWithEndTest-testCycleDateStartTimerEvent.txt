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
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventRepeatWithEndTest.testCycleDateStartTimerEvent.bpmn20.xml").deploy();
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
  moveByMinutes(60 * 25);
  waitForJobExecutorToProcessAllJobs(2000,200);
  jobs=managementService.createTimerJobQuery().list();
  assertEquals(1,jobs.size());
  processInstances=runtimeService.createProcessInstanceQuery().list();
  assertEquals(1,processInstances.size());
  tasks=taskService.createTaskQuery().list();
  assertEquals(1,tasks.size());
  jobs=managementService.createTimerJobQuery().list();
  assertEquals(1,jobs.size());
  dueDateCalendar=Calendar.getInstance();
  dueDateCalendar.set(2025,Calendar.DECEMBER,12,0,0,0);
  assertEquals(true,Math.abs(dueDateCalendar.getTime().getTime() - jobs.get(0).getDuedate().getTime()) < 2000);
  moveByMinutes(60 * 25);
  try {
    waitForJobExecutorToProcessAllJobs(10000,200);
  }
 catch (  Exception e) {
    fail("Because the endDate is reached no other jobs created");
  }
  processInstances=runtimeService.createProcessInstanceQuery().list();
  assertEquals(2,processInstances.size());
  jobs=managementService.createTimerJobQuery().list();
  assertEquals(0,jobs.size());
  jobs=managementService.createJobQuery().list();
  assertEquals(0,jobs.size());
  tasks=taskService.createTaskQuery().list();
  assertEquals(2,tasks.size());
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
  assertEquals(2,timerFiredCount);
  assertEquals(4,eventCreatedCount);
  assertEquals(4,eventDeletedCount);
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
