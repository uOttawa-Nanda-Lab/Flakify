@Test public void assertPutNullValue() throws NoSuchFieldException {
  JobProperties actual=new JobProperties();
  actual.put(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER.getKey(),null);
  assertTrue(getMap(actual).isEmpty());
}
