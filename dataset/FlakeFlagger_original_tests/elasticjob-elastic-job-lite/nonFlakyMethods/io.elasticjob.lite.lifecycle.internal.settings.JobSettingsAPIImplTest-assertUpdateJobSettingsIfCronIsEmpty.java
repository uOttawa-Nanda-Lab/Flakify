@Test(expected=IllegalArgumentException.class) public void assertUpdateJobSettingsIfCronIsEmpty(){
  JobSettings jobSettings=new JobSettings();
  jobSettings.setJobName("test_job");
  jobSettings.setCron("");
  jobSettingsAPI.updateJobSettings(jobSettings);
}
