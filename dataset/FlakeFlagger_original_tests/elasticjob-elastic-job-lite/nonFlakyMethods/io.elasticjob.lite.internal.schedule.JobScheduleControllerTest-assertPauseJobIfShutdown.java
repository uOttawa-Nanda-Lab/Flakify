@Test public void assertPauseJobIfShutdown() throws NoSuchFieldException, SchedulerException {
  when(scheduler.isShutdown()).thenReturn(true);
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  jobScheduleController.pauseJob();
  verify(scheduler,times(0)).pauseAll();
}
