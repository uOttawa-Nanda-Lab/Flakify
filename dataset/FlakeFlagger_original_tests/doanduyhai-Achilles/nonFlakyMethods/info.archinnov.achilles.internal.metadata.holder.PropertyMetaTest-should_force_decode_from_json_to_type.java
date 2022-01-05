@Test public void should_force_decode_from_json_to_type() throws Exception {
  PropertyMeta pm=new PropertyMeta();
  pm.setTranscoder(transcoder);
  when(transcoder.forceDecodeFromJSON("test",String.class)).thenReturn("test");
  assertThat(pm.forceDecodeFromJSON("test",String.class)).isEqualTo("test");
}
