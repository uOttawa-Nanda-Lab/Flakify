@Test public void should_force_encode_to_json_string_type() throws Exception {
  assertThat(transcoder.forceEncodeToJSON("test")).isEqualTo("test");
  verifyZeroInteractions(objectMapper);
}
