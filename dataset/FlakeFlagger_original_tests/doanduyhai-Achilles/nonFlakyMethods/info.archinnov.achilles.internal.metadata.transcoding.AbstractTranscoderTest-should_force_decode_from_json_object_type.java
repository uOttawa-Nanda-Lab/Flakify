@Test public void should_force_decode_from_json_object_type() throws Exception {
  when(objectMapper.readValue("10",Long.class)).thenReturn(10L);
  assertThat(transcoder.forceDecodeFromJSON("10",Long.class)).isEqualTo(10L);
}
