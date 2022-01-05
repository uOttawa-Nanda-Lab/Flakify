/** 
 * Timer repetition
 */
@Deployment public void testRepetitionJobEntityEvents() throws Exception {
  Clock previousClock=processEngineConfiguration.getClock();
  Clock testClock=new DefaultClockImpl();
  processEngineConfiguration.setClock(testClock);
  Date now=new Date();
  testClock.setCurrentTime(now);
  Calendar nowCalendar=new GregorianCalendar();
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testRepetitionJobEvents");
  Job theJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(theJob);
  assertEquals(3,listener.getEventsReceived().size());
  ActivitiEvent event=listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
  checkEventContext(event,theJob);
  event=listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
  checkEventContext(event,theJob);
  event=listener.getEventsReceived().get(2);
  assertEquals(ActivitiEventType.TIMER_SCHEDULED,event.getType());
  checkEventContext(event,theJob);
  listener.clearEventsReceived();
  waitForJobExecutorToProcessAllJobs(2000,200);
  assertEquals(0,listener.getEventsReceived().size());
  assertEquals(1,managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).count());
  Job firstTimerInstance=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  nowCalendar.add(Calendar.HOUR,1);
  nowCalendar.add(Calendar.MINUTE,5);
  testClock.setCurrentTime(nowCalendar.getTime());
  waitForJobExecutorToProcessAllJobs(2000,200);
  assertEquals(1,managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).count());
  Job secondTimerInstance=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  assertTrue(firstTimerInstance.getId() != secondTimerInstance.getId());
  checkEventCount(1,ActivitiEventType.TIMER_FIRED);
  checkEventContext(filterEvents(ActivitiEventType.TIMER_FIRED).get(0),firstTimerInstance);
  checkEventCount(1,ActivitiEventType.TIMER_SCHEDULED);
  checkEventContext(filterEvents(ActivitiEventType.TIMER_SCHEDULED).get(0),secondTimerInstance);
  listener.clearEventsReceived();
  nowCalendar.add(Calendar.HOUR,1);
  nowCalendar.add(Calendar.MINUTE,5);
  testClock.setCurrentTime(nowCalendar.getTime());
  waitForJobExecutorToProcessAllJobs(2000,200);
  assertEquals(0,managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).count());
  nowCalendar.add(Calendar.HOUR,1);
  nowCalendar.add(Calendar.MINUTE,5);
  testClock.setCurrentTime(nowCalendar.getTime());
  waitForJobExecutorToProcessAllJobs(2000,200);
  assertEquals(0,managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).count());
  checkEventCount(1,ActivitiEventType.TIMER_FIRED);
  checkEventContext(filterEvents(ActivitiEventType.TIMER_FIRED).get(0),secondTimerInstance);
  checkEventCount(0,ActivitiEventType.TIMER_SCHEDULED);
  listener.clearEventsReceived();
  processEngineConfiguration.setClock(previousClock);
}
