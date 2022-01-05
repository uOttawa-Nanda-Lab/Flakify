@Test public void should_return_null_when_force_decode_null() throws Exception {
  assertThat(transcoder.forceDecodeFromJSON(null,Long.class)).isNull();
}
