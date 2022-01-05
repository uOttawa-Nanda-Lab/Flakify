/** 
 * Test create, update and delete events of jobs entities.
 */
@Deployment public void testJobEntityEvents() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testJobEvents");
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
  managementService.setTimerJobRetries(theJob.getId(),5);
  assertEquals(1,listener.getEventsReceived().size());
  event=listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ENTITY_UPDATED,event.getType());
  Job updatedJob=(Job)((ActivitiEntityEvent)event).getEntity();
  assertEquals(5,updatedJob.getRetries());
  checkEventContext(event,theJob);
  checkEventCount(0,ActivitiEventType.TIMER_SCHEDULED);
  listener.clearEventsReceived();
  Calendar tomorrow=Calendar.getInstance();
  tomorrow.add(Calendar.DAY_OF_YEAR,1);
  processEngineConfiguration.getClock().setCurrentTime(tomorrow.getTime());
  String jobId=managementService.createTimerJobQuery().singleResult().getId();
  managementService.moveTimerToExecutableJob(jobId);
  managementService.executeJob(jobId);
  assertEquals(6,listener.getEventsReceived().size());
  event=listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
  checkEventContext(event,theJob);
  event=listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
  checkEventContext(event,theJob);
  event=listener.getEventsReceived().get(3);
  assertEquals(ActivitiEventType.TIMER_FIRED,event.getType());
  checkEventContext(event,theJob);
  event=listener.getEventsReceived().get(4);
  assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
  checkEventContext(event,theJob);
  event=listener.getEventsReceived().get(5);
  assertEquals(ActivitiEventType.JOB_EXECUTION_SUCCESS,event.getType());
  checkEventContext(event,theJob);
  checkEventCount(0,ActivitiEventType.TIMER_SCHEDULED);
}
