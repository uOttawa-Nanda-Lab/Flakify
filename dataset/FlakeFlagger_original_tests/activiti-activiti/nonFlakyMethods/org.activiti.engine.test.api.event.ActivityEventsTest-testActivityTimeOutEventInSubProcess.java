@Deployment(resources="org/activiti/engine/test/bpmn/event/timer/BoundaryTimerEventTest.testTimerOnNestingOfSubprocesses.bpmn20.xml") public void testActivityTimeOutEventInSubProcess(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("timerOnNestedSubprocesses");
  Job theJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(theJob);
  Calendar timeToFire=Calendar.getInstance();
  timeToFire.add(Calendar.HOUR,2);
  timeToFire.add(Calendar.SECOND,5);
  processEngineConfiguration.getClock().setCurrentTime(timeToFire.getTime());
  waitForJobExecutorToProcessAllJobs(2000,200);
  assertEquals(4,listener.getEventsReceived().size());
  List<String> eventIdList=new ArrayList<String>();
  for (  ActivitiEvent event : listener.getEventsReceived()) {
    assertEquals(ActivitiEventType.ACTIVITY_CANCELLED,event.getType());
    assertTrue("TIMER is the cause of the cancellation",((ActivitiActivityCancelledEvent)event).getCause() instanceof JobEntity);
    eventIdList.add(((ActivitiActivityEventImpl)event).getActivityId());
  }
  assertTrue(eventIdList.indexOf("innerTask1") >= 0);
  assertTrue(eventIdList.indexOf("innerTask2") >= 0);
  assertTrue(eventIdList.indexOf("subprocess") >= 0);
  assertTrue(eventIdList.indexOf("innerSubprocess") >= 0);
}
