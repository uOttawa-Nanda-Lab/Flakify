@Test public void assertCreateJobOperateAPI(){
  assertThat(JobAPIFactory.createJobOperateAPI(getConnectionString(),"namespace",Optional.<String>absent()),instanceOf(JobOperateAPI.class));
}
