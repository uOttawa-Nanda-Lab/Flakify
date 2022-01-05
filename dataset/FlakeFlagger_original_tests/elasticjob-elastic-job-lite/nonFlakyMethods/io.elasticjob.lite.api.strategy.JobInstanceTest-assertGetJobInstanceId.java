@Test public void assertGetJobInstanceId(){
  assertThat(new JobInstance("127.0.0.1@-@0").getJobInstanceId(),is("127.0.0.1@-@0"));
}
