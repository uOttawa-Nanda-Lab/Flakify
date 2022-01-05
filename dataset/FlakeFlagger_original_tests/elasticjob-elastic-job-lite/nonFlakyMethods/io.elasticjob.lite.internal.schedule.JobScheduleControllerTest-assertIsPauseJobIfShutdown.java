@Test public void assertIsPauseJobIfShutdown() throws NoSuchFieldException, SchedulerException {
  when(scheduler.isShutdown()).thenReturn(true);
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  assertFalse(jobScheduleController.isPaused());
}
