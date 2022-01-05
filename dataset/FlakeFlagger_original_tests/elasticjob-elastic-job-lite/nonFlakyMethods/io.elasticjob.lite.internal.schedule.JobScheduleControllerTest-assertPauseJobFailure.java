@Test(expected=JobSystemException.class) public void assertPauseJobFailure() throws NoSuchFieldException, SchedulerException {
  doThrow(SchedulerException.class).when(scheduler).pauseAll();
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  try {
    jobScheduleController.pauseJob();
  }
  finally {
    verify(scheduler).pauseAll();
  }
}
