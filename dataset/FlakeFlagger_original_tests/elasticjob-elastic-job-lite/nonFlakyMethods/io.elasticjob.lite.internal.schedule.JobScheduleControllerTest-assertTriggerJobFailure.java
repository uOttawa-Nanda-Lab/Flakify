@Test(expected=JobSystemException.class) public void assertTriggerJobFailure() throws NoSuchFieldException, SchedulerException {
  JobKey jobKey=new JobKey("test_job");
  when(jobDetail.getKey()).thenReturn(jobKey);
  doThrow(SchedulerException.class).when(scheduler).triggerJob(jobKey);
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  ReflectionUtils.setFieldValue(jobScheduleController,"jobDetail",jobDetail);
  try {
    jobScheduleController.triggerJob();
  }
  finally {
    verify(jobDetail).getKey();
    verify(scheduler).triggerJob(jobKey);
  }
}
