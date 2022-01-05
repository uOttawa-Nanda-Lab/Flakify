@Test public void assertGetWhenValueIsEmpty() throws NoSuchFieldException {
  JobProperties actual=new JobProperties();
  assertThat(actual.get(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER),is(DefaultJobExceptionHandler.class.getCanonicalName()));
  assertThat(actual.get(JobProperties.JobPropertiesEnum.EXECUTOR_SERVICE_HANDLER),is(DefaultExecutorServiceHandler.class.getCanonicalName()));
}
