@Test public void assertShutdownJobIfShutdown() throws NoSuchFieldException, SchedulerException {
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  when(scheduler.isShutdown()).thenReturn(true);
  jobScheduleController.shutdown();
  verify(scheduler,times(0)).shutdown();
}
