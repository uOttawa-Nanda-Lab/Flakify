@Test public void assertTriggerJobSuccess() throws NoSuchFieldException, SchedulerException {
  JobKey jobKey=new JobKey("test_job");
  when(jobDetail.getKey()).thenReturn(jobKey);
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  ReflectionUtils.setFieldValue(jobScheduleController,"jobDetail",jobDetail);
  jobScheduleController.triggerJob();
  verify(jobDetail).getKey();
  verify(scheduler).triggerJob(jobKey);
}
