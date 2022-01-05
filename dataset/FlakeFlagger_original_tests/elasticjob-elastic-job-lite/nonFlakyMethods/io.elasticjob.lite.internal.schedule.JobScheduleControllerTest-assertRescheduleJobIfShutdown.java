@Test public void assertRescheduleJobIfShutdown() throws NoSuchFieldException, SchedulerException {
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  when(scheduler.isShutdown()).thenReturn(true);
  jobScheduleController.rescheduleJob("0/1 * * * * ?");
  verify(scheduler,times(0)).rescheduleJob(eq(TriggerKey.triggerKey("test_job_Trigger")),ArgumentMatchers.<Trigger>any());
}
