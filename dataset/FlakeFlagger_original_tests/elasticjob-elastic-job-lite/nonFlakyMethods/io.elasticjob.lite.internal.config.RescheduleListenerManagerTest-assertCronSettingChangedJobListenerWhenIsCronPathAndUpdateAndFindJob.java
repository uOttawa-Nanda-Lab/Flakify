@Test public void assertCronSettingChangedJobListenerWhenIsCronPathAndUpdateAndFindJob(){
  JobRegistry.getInstance().addJobInstance("test_job",new JobInstance("127.0.0.1@-@0"));
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  rescheduleListenerManager.new CronSettingAndJobEventChangedJobListener().dataChanged("/test_job/config",Type.NODE_UPDATED,LiteJsonConstants.getJobJson());
  verify(jobScheduleController).rescheduleJob("0/1 * * * * ?");
  JobRegistry.getInstance().shutdown("test_job");
}
