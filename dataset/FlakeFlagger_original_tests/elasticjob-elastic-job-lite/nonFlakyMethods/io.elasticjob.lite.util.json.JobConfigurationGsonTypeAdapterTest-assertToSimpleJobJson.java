@Test public void assertToSimpleJobJson(){
  assertThat(GsonFactory.getGson().toJson(new TestJobRootConfiguration(new TestSimpleJobConfiguration(ThrowJobExceptionHandler.class.getCanonicalName(),DefaultExecutorServiceHandler.class.getCanonicalName()).getTypeConfig())),is(APIJsonConstants.getSimpleJobJson(ThrowJobExceptionHandler.class.getCanonicalName())));
}
