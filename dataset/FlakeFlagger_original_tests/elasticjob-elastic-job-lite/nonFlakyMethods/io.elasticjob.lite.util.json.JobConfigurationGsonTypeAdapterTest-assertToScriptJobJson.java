@Test public void assertToScriptJobJson(){
  assertThat(GsonFactory.getGson().toJson(new TestJobRootConfiguration(new TestScriptJobConfiguration("test.sh",ThrowJobExceptionHandler.class).getTypeConfig())),is(APIJsonConstants.getScriptJobJson(ThrowJobExceptionHandler.class.getCanonicalName())));
}
