@Test public void should_encode_supported_type() throws Exception {
  Object actual=transcoder.encodeInternal(String.class,"value");
  assertThat(actual).isEqualTo("value");
}
