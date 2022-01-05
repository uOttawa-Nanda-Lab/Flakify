@Test public void assertIsPausedIfTriggerStateIsNormal() throws NoSuchFieldException, SchedulerException {
  when(scheduler.getTriggerState(new TriggerKey("test_job_Trigger"))).thenReturn(Trigger.TriggerState.NORMAL);
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  assertFalse(jobScheduleController.isPaused());
}
