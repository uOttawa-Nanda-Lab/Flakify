@Deployment public void testTerminateEndEvent() throws Exception {
  Clock previousClock=processEngineConfiguration.getClock();
  TestActivitiEventListener activitiEventListener=new TestActivitiEventListener();
  processEngineConfiguration.getEventDispatcher().addEventListener(activitiEventListener);
  Clock testClock=new DefaultClockImpl();
  processEngineConfiguration.setClock(testClock);
  testClock.setCurrentTime(new Date());
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testTerminateEndEvent");
  listener.clearEventsReceived();
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("Inside Task",task.getName());
  Calendar later=Calendar.getInstance();
  later.add(Calendar.YEAR,1);
  processEngineConfiguration.getClock().setCurrentTime(later.getTime());
  waitForJobExecutorToProcessAllJobs(2000,100);
  List<ActivitiEvent> eventsReceived=activitiEventListener.getEventsReceived();
  for (  ActivitiEvent eventReceived : eventsReceived) {
    if (ActivitiEventType.PROCESS_CANCELLED.equals(eventReceived.getType())) {
      fail("Should not have received PROCESS_CANCELLED event");
    }
  }
  for (  ActivitiEvent eventReceived : eventsReceived) {
    if (ActivitiEventType.ACTIVITY_CANCELLED.equals(eventReceived.getType())) {
      ActivitiActivityEvent event=(ActivitiActivityEvent)eventReceived;
      String activityType=event.getActivityType();
      if (!"userTask".equals(activityType) && (!"subProcess".equals(activityType)) && (!"endEvent".equals(activityType))) {
        fail("Unexpected activity type: " + activityType);
      }
    }
  }
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("Outside Task",task.getName());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  processEngineConfiguration.setClock(previousClock);
}
