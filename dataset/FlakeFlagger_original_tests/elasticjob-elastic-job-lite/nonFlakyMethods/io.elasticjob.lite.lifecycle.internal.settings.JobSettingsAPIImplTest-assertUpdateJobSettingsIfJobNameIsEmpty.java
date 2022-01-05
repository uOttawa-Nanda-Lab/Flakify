@Test(expected=IllegalArgumentException.class) public void assertUpdateJobSettingsIfJobNameIsEmpty(){
  JobSettings jobSettings=new JobSettings();
  jobSettings.setJobName("");
  jobSettingsAPI.updateJobSettings(jobSettings);
}
