@Test(expected=JobSystemException.class) public void assertIsPausedFailure() throws NoSuchFieldException, SchedulerException {
  doThrow(SchedulerException.class).when(scheduler).getTriggerState(new TriggerKey("test_job_Trigger"));
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  try {
    jobScheduleController.isPaused();
  }
  finally {
    verify(scheduler).getTriggerState(new TriggerKey("test_job_Trigger"));
  }
}
