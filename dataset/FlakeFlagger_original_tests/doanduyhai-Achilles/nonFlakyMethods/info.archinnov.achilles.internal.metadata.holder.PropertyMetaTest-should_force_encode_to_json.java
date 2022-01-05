@Test public void should_force_encode_to_json() throws Exception {
  PropertyMeta pm=new PropertyMeta();
  pm.setTranscoder(transcoder);
  pm.forceEncodeToJSON("value");
  verify(transcoder).forceEncodeToJSON("value");
}
