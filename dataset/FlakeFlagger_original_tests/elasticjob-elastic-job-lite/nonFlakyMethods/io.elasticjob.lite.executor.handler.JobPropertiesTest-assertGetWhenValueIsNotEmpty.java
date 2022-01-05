@Test public void assertGetWhenValueIsNotEmpty() throws NoSuchFieldException {
  JobProperties actual=new JobProperties();
  actual.put(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER.getKey(),IgnoreJobExceptionHandler.class.getCanonicalName());
  assertThat(actual.get(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER),is(IgnoreJobExceptionHandler.class.getCanonicalName()));
}
