@Test public void assertPutSuccess() throws NoSuchFieldException {
  JobProperties actual=new JobProperties();
  actual.put(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER.getKey(),DefaultJobExceptionHandler.class.getCanonicalName());
  assertThat(getMap(actual).size(),is(1));
}
