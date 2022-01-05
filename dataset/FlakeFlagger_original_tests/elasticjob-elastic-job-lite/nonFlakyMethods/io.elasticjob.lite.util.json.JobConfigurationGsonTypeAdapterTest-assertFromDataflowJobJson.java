@Test public void assertFromDataflowJobJson(){
  TestJobRootConfiguration actual=GsonFactory.getGson().fromJson(APIJsonConstants.getDataflowJobJson(IgnoreJobExceptionHandler.class.getCanonicalName()),TestJobRootConfiguration.class);
  TestJobRootConfiguration expected=new TestJobRootConfiguration(new TestDataflowJobConfiguration(true).getTypeConfig());
  assertThat(GsonFactory.getGson().toJson(actual),is(GsonFactory.getGson().toJson(expected)));
}
