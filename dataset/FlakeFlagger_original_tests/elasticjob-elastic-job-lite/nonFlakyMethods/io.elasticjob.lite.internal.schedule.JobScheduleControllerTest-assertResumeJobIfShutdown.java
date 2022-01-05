@Test public void assertResumeJobIfShutdown() throws NoSuchFieldException, SchedulerException {
  when(scheduler.isShutdown()).thenReturn(true);
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  jobScheduleController.resumeJob();
  verify(scheduler,times(0)).resumeAll();
}
