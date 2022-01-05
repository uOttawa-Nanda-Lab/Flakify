@Test public void should_force_decode_from_json_simple_value() throws Exception {
  PropertyMeta pm=new PropertyMeta();
  pm.setTranscoder(transcoder);
  pm.setType(SIMPLE);
  pm.setValueClass(String.class);
  when(transcoder.forceDecodeFromJSON("value",String.class)).thenReturn("decoded");
  Object decoded=pm.forceDecodeFromJSON("value");
  assertThat(decoded).isEqualTo("decoded");
}
