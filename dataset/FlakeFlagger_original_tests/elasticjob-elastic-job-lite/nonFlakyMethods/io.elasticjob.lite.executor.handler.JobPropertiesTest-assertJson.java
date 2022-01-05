@Test public void assertJson(){
  assertThat(new JobProperties().json(),is(APIJsonConstants.getJobPropertiesJson(DefaultJobExceptionHandler.class.getCanonicalName())));
}
