@Test public void assertGetMessage(){
  assertThat(new JobSystemException("message is: '%s'","test").getMessage(),is("message is: 'test'"));
}
