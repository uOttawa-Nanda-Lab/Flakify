/** 
 * /** Test TIMER_FIRED event for timer start bpmn event.
 */
@Deployment public void testTimerFiredForTimerStart() throws Exception {
  Calendar tomorrow=Calendar.getInstance();
  tomorrow.add(Calendar.DAY_OF_YEAR,1);
  processEngineConfiguration.getClock().setCurrentTime(tomorrow.getTime());
  waitForJobExecutorToProcessAllJobs(2000,100);
  assertEquals(6,listener.getEventsReceived().size());
  assertEquals(ActivitiEventType.ENTITY_CREATED,listener.getEventsReceived().get(0).getType());
  assertEquals(ActivitiEventType.ENTITY_INITIALIZED,listener.getEventsReceived().get(1).getType());
  assertEquals(ActivitiEventType.ENTITY_DELETED,listener.getEventsReceived().get(2).getType());
  assertEquals(ActivitiEventType.TIMER_FIRED,listener.getEventsReceived().get(3).getType());
  assertEquals(ActivitiEventType.JOB_EXECUTION_SUCCESS,listener.getEventsReceived().get(5).getType());
  checkEventCount(0,ActivitiEventType.JOB_CANCELED);
}
