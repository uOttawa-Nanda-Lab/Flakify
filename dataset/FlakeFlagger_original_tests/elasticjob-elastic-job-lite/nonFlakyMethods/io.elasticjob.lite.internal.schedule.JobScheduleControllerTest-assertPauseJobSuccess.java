@Test public void assertPauseJobSuccess() throws NoSuchFieldException, SchedulerException {
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  jobScheduleController.pauseJob();
  verify(scheduler).pauseAll();
}
