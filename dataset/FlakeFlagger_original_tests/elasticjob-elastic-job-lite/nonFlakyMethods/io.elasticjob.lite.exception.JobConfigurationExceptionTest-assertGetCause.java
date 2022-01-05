@Test public void assertGetCause(){
  assertThat(new JobConfigurationException(new RuntimeException()).getCause(),instanceOf(RuntimeException.class));
}
