@Test public void assertGetCause(){
  assertThat(new JobStatisticException(new RuntimeException()).getCause(),instanceOf(RuntimeException.class));
}
