@Test public void assertCronSettingChangedJobListenerWhenIsCronPathAndUpdateButCannotFindJob(){
  rescheduleListenerManager.new CronSettingAndJobEventChangedJobListener().dataChanged("/test_job/config",Type.NODE_UPDATED,LiteJsonConstants.getJobJson());
  verify(jobScheduleController,times(0)).rescheduleJob(ArgumentMatchers.<String>any());
}
