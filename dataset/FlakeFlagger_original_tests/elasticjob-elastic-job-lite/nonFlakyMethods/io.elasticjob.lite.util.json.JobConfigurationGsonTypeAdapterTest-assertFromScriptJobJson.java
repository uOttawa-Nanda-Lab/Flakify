@Test public void assertFromScriptJobJson(){
  TestJobRootConfiguration actual=GsonFactory.getGson().fromJson(APIJsonConstants.getScriptJobJson(ThrowJobExceptionHandler.class.getCanonicalName()),TestJobRootConfiguration.class);
  TestJobRootConfiguration expected=new TestJobRootConfiguration(new TestScriptJobConfiguration("test.sh",ThrowJobExceptionHandler.class).getTypeConfig());
  assertThat(GsonFactory.getGson().toJson(actual),is(GsonFactory.getGson().toJson(expected)));
}
