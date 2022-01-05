@Test public void should_decode_supported_type() throws Exception {
  Object actual=transcoder.decodeInternal(String.class,"value");
  assertThat(actual).isEqualTo("value");
}
