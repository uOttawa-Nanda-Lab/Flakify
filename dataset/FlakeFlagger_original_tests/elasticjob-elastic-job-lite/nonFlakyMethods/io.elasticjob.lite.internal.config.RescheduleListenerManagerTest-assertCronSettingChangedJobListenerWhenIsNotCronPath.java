@Test public void assertCronSettingChangedJobListenerWhenIsNotCronPath(){
  rescheduleListenerManager.new CronSettingAndJobEventChangedJobListener().dataChanged("/test_job/config/other",Type.NODE_ADDED,LiteJsonConstants.getJobJson());
  verify(jobScheduleController,times(0)).rescheduleJob(ArgumentMatchers.<String>any());
}
