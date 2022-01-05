/** 
 * Test TIMER_FIRED event for intermediate timer bpmn event.
 */
@Deployment public void testTimerFiredForIntermediateTimer() throws Exception {
  runtimeService.startProcessInstanceByKey("testTimerFiredForIntermediateTimer");
  Calendar tomorrow=Calendar.getInstance();
  tomorrow.add(Calendar.DAY_OF_YEAR,1);
  processEngineConfiguration.getClock().setCurrentTime(tomorrow.getTime());
  waitForJobExecutorToProcessAllJobs(2000,100);
  checkEventCount(1,ActivitiEventType.TIMER_SCHEDULED);
  checkEventCount(0,ActivitiEventType.JOB_CANCELED);
  checkEventCount(1,ActivitiEventType.TIMER_FIRED);
}
