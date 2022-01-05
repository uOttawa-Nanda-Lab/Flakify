@Test public void assertCreateJobSettingsAPI(){
  assertThat(JobAPIFactory.createJobSettingsAPI(getConnectionString(),"namespace",Optional.<String>absent()),instanceOf(JobSettingsAPI.class));
}
