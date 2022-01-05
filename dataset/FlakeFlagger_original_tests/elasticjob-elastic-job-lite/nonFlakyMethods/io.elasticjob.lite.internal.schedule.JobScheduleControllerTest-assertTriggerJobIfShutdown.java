@Test public void assertTriggerJobIfShutdown() throws NoSuchFieldException, SchedulerException {
  JobKey jobKey=new JobKey("test_job");
  when(jobDetail.getKey()).thenReturn(jobKey);
  when(scheduler.isShutdown()).thenReturn(true);
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  ReflectionUtils.setFieldValue(jobScheduleController,"jobDetail",jobDetail);
  jobScheduleController.triggerJob();
  verify(jobDetail,times(0)).getKey();
  verify(scheduler,times(0)).triggerJob(jobKey);
}
