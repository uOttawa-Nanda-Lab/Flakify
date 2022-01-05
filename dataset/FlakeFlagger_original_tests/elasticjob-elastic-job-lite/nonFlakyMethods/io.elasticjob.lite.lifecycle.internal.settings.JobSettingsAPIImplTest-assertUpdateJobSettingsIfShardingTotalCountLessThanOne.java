@Test(expected=IllegalArgumentException.class) public void assertUpdateJobSettingsIfShardingTotalCountLessThanOne(){
  JobSettings jobSettings=new JobSettings();
  jobSettings.setJobName("test_job");
  jobSettings.setCron("0/1 * * * * ?");
  jobSettings.setShardingTotalCount(0);
  jobSettingsAPI.updateJobSettings(jobSettings);
}
