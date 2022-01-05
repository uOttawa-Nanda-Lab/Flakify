/** 
 * Test create, update and delete events of jobs entities.
 */
@Deployment public void testJobEntityEventsException() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testJobEvents");
  Job theJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(theJob);
  managementService.setTimerJobRetries(theJob.getId(),1);
  Calendar tomorrow=Calendar.getInstance();
  tomorrow.add(Calendar.DAY_OF_YEAR,1);
  processEngineConfiguration.getClock().setCurrentTime(tomorrow.getTime());
  Job executableJob=managementService.moveTimerToExecutableJob(theJob.getId());
  listener.clearEventsReceived();
  try {
    managementService.executeJob(executableJob.getId());
    fail("Expected exception");
  }
 catch (  Exception e) {
  }
  theJob=managementService.createDeadLetterJobQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(theJob);
  assertEquals(8,listener.getEventsReceived().size());
  ActivitiEvent event=listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.TIMER_FIRED,event.getType());
  checkEventContext(event,theJob);
  event=listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
  checkEventContext(event,theJob);
  event=listener.getEventsReceived().get(2);
  assertEquals(ActivitiEventType.JOB_EXECUTION_FAILURE,event.getType());
  checkEventContext(event,theJob);
  event=listener.getEventsReceived().get(3);
  assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
  checkEventContext(event,theJob);
  event=listener.getEventsReceived().get(4);
  assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
  checkEventContext(event,theJob);
  event=listener.getEventsReceived().get(5);
  assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
  checkEventContext(event,theJob);
  event=listener.getEventsReceived().get(6);
  assertEquals(ActivitiEventType.ENTITY_UPDATED,event.getType());
  checkEventContext(event,theJob);
  event=listener.getEventsReceived().get(7);
  assertEquals(ActivitiEventType.JOB_RETRIES_DECREMENTED,event.getType());
  assertEquals(0,((Job)((ActivitiEntityEvent)event).getEntity()).getRetries());
  checkEventContext(event,theJob);
}
