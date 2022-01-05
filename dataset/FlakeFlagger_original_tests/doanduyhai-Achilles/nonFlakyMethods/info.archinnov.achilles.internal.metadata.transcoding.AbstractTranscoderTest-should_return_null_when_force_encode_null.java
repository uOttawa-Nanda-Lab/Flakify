@Test public void should_return_null_when_force_encode_null() throws Exception {
  assertThat(transcoder.forceEncodeToJSON(null)).isNull();
}
