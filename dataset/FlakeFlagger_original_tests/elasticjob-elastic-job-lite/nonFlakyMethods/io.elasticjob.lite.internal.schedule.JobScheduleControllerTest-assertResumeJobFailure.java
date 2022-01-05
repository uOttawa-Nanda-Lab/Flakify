@Test(expected=JobSystemException.class) public void assertResumeJobFailure() throws NoSuchFieldException, SchedulerException {
  doThrow(SchedulerException.class).when(scheduler).resumeAll();
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  try {
    jobScheduleController.resumeJob();
  }
  finally {
    verify(scheduler).resumeAll();
  }
}
