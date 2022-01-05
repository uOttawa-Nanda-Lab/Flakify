@Deployment public void testJobCanceledEventOnBoundaryEvent() throws Exception {
  Clock testClock=new DefaultClockImpl();
  processEngineConfiguration.setClock(testClock);
  testClock.setCurrentTime(new Date());
  runtimeService.startProcessInstanceByKey("testTimerCancelledEvent");
  listener.clearEventsReceived();
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  checkEventCount(1,ActivitiEventType.JOB_CANCELED);
}
