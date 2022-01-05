@Test public void should_serialize_as_json() throws Exception {
  SimpleTranscoder transcoder=new SimpleTranscoder(objectMapper);
  PropertyMeta pm=new PropertyMeta();
  pm.setType(SIMPLE);
  pm.setTranscoder(transcoder);
  assertThat(pm.forceEncodeToJSON(new UUID(10,10))).isEqualTo("\"00000000-0000-000a-0000-00000000000a\"");
}
