@Deployment(resources="org/activiti/engine/test/api/event/JobEventsTest.testJobEntityEvents.bpmn20.xml") public void testActivityTimeOutEvent(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testJobEvents");
  Job theJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(theJob);
  Calendar tomorrow=Calendar.getInstance();
  tomorrow.add(Calendar.DAY_OF_YEAR,1);
  processEngineConfiguration.getClock().setCurrentTime(tomorrow.getTime());
  waitForJobExecutorToProcessAllJobs(2000,1000);
  assertEquals(1,listener.getEventsReceived().size());
  ActivitiEvent activitiEvent=listener.getEventsReceived().get(0);
  assertEquals("ACTIVITY_CANCELLED event expected",ActivitiEventType.ACTIVITY_CANCELLED,activitiEvent.getType());
  ActivitiActivityCancelledEvent cancelledEvent=(ActivitiActivityCancelledEvent)activitiEvent;
  assertTrue("TIMER is the cause of the cancellation",cancelledEvent.getCause() instanceof JobEntity);
}
