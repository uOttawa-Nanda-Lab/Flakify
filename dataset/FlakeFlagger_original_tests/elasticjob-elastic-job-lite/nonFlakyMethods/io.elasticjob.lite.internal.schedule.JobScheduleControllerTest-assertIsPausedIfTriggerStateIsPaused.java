@Test public void assertIsPausedIfTriggerStateIsPaused() throws NoSuchFieldException, SchedulerException {
  when(scheduler.getTriggerState(new TriggerKey("test_job_Trigger"))).thenReturn(Trigger.TriggerState.PAUSED);
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  assertTrue(jobScheduleController.isPaused());
}
