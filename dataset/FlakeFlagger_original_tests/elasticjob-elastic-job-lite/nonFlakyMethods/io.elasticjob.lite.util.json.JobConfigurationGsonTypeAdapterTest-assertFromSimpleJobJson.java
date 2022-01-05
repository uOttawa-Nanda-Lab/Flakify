@Test public void assertFromSimpleJobJson(){
  TestJobRootConfiguration actual=GsonFactory.getGson().fromJson(APIJsonConstants.getSimpleJobJson(ThrowJobExceptionHandler.class.getCanonicalName()),TestJobRootConfiguration.class);
  TestJobRootConfiguration expected=new TestJobRootConfiguration(new TestSimpleJobConfiguration(ThrowJobExceptionHandler.class.getCanonicalName(),DefaultExecutorServiceHandler.class.getCanonicalName()).getTypeConfig());
  assertThat(GsonFactory.getGson().toJson(actual),is(GsonFactory.getGson().toJson(expected)));
}
