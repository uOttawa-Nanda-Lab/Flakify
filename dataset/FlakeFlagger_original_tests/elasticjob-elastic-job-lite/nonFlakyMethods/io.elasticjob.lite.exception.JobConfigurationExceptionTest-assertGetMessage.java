@Test public void assertGetMessage(){
  assertThat(new JobConfigurationException("message is: '%s'","test").getMessage(),is("message is: 'test'"));
}
