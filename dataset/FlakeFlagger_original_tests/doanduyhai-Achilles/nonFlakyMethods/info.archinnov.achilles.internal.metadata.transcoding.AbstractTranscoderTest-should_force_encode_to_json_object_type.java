@Test public void should_force_encode_to_json_object_type() throws Exception {
  when(objectMapper.writeValueAsString(10L)).thenReturn("10");
  assertThat(transcoder.forceEncodeToJSON(10L)).isEqualTo("10");
}
