@Test(expected=JobSystemException.class) public void assertRescheduleJobFailure() throws NoSuchFieldException, SchedulerException {
  when(scheduler.getTrigger(TriggerKey.triggerKey("test_job_Trigger"))).thenReturn(new CronTriggerImpl());
  doThrow(SchedulerException.class).when(scheduler).rescheduleJob(eq(TriggerKey.triggerKey("test_job_Trigger")),ArgumentMatchers.<Trigger>any());
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  try {
    jobScheduleController.rescheduleJob("0/1 * * * * ?");
  }
  finally {
    verify(scheduler).rescheduleJob(eq(TriggerKey.triggerKey("test_job_Trigger")),ArgumentMatchers.<Trigger>any());
  }
}
