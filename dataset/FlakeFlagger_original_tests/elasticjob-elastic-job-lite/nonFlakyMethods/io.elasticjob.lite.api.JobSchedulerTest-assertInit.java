@Test public void assertInit() throws NoSuchFieldException, SchedulerException {
  when(schedulerFacade.updateJobConfiguration(liteJobConfig)).thenReturn(liteJobConfig);
  when(schedulerFacade.newJobTriggerListener()).thenReturn(new JobTriggerListener(null,null));
  jobScheduler.init();
  verify(schedulerFacade).registerStartUpInfo(true);
  Scheduler scheduler=ReflectionUtils.getFieldValue(JobRegistry.getInstance().getJobScheduleController("test_job"),JobScheduleController.class.getDeclaredField("scheduler"));
  assertThat(scheduler.getListenerManager().getTriggerListeners().get(0),instanceOf(JobTriggerListener.class));
  assertTrue(scheduler.isStarted());
}
