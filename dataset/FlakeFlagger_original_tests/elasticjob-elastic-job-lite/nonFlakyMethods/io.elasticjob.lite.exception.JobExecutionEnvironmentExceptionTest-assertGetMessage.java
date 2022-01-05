@Test public void assertGetMessage(){
  assertThat(new JobExecutionEnvironmentException("message is: '%s'","test").getMessage(),is("message is: 'test'"));
}
