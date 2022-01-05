@Test public void assertShutdownSuccess() throws NoSuchFieldException, SchedulerException {
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  jobScheduleController.shutdown();
  verify(scheduler).shutdown();
}
