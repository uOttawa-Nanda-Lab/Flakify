@Test public void assertRescheduleJobWhenTriggerIsNull() throws NoSuchFieldException, SchedulerException {
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  jobScheduleController.rescheduleJob("0/1 * * * * ?");
  verify(scheduler,times(0)).rescheduleJob(eq(TriggerKey.triggerKey("test_job_Trigger")),ArgumentMatchers.<Trigger>any());
}
