@Test public void assertRescheduleJobSuccess() throws NoSuchFieldException, SchedulerException {
  when(scheduler.getTrigger(TriggerKey.triggerKey("test_job_Trigger"))).thenReturn(new CronTriggerImpl());
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  jobScheduleController.rescheduleJob("0/1 * * * * ?");
  verify(scheduler).rescheduleJob(eq(TriggerKey.triggerKey("test_job_Trigger")),ArgumentMatchers.<Trigger>any());
}
