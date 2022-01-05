@Test public void assertGetCause(){
  assertThat(new JobSystemException(new RuntimeException()).getCause(),instanceOf(RuntimeException.class));
}
