@Test public void assertResumeJobSuccess() throws NoSuchFieldException, SchedulerException {
  ReflectionUtils.setFieldValue(jobScheduleController,"scheduler",scheduler);
  jobScheduleController.resumeJob();
  verify(scheduler).resumeAll();
}
