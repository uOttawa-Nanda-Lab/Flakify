@Test public void should_force_decode_to_json_string_type() throws Exception {
  assertThat(transcoder.forceDecodeFromJSON("test",String.class)).isEqualTo("test");
  verifyZeroInteractions(objectMapper);
}
