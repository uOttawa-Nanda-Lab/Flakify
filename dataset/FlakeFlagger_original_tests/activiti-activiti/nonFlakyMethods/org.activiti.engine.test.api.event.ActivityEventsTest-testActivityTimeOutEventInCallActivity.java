@Deployment public void testActivityTimeOutEventInCallActivity(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("timerOnCallActivity");
  Job theJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(theJob);
  Calendar timeToFire=Calendar.getInstance();
  timeToFire.add(Calendar.HOUR,2);
  timeToFire.add(Calendar.MINUTE,5);
  processEngineConfiguration.getClock().setCurrentTime(timeToFire.getTime());
  waitForJobExecutorToProcessAllJobs(5000,500);
  assertEquals(4,listener.getEventsReceived().size());
  List<String> eventIdList=new ArrayList<String>();
  for (  ActivitiEvent event : listener.getEventsReceived()) {
    assertEquals(ActivitiEventType.ACTIVITY_CANCELLED,event.getType());
    assertTrue("TIMER is the cause of the cancellation",((ActivitiActivityCancelledEvent)event).getCause() instanceof JobEntity);
    eventIdList.add(((ActivitiActivityEventImpl)event).getActivityId());
  }
  assertTrue(eventIdList.contains("innerTask1"));
  assertTrue(eventIdList.contains("innerTask2"));
  assertTrue(eventIdList.contains("callActivity"));
  assertTrue(eventIdList.contains("innerSubprocess"));
}
