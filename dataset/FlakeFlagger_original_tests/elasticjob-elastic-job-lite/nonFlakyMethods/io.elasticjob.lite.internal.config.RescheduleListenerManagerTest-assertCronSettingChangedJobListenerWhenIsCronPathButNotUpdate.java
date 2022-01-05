@Test public void assertCronSettingChangedJobListenerWhenIsCronPathButNotUpdate(){
  rescheduleListenerManager.new CronSettingAndJobEventChangedJobListener().dataChanged("/test_job/config",Type.NODE_ADDED,LiteJsonConstants.getJobJson());
  verify(jobScheduleController,times(0)).rescheduleJob(ArgumentMatchers.<String>any());
}
