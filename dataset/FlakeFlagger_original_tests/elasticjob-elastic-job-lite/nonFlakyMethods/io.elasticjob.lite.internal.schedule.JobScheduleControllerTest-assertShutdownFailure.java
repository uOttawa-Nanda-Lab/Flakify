@Test(expected=JobSystemException.class) public void assertShutdownFailure() throws NoSuchFieldException, SchedulerException {
  doThrow(SchedulerException.class).when(scheduler).shutdown();
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  try {
    jobScheduleController.shutdown();
  }
  finally {
    verify(scheduler).shutdown();
  }
}
