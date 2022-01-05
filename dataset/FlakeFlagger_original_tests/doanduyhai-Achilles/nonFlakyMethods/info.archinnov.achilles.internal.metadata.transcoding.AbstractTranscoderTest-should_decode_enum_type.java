@Test public void should_decode_enum_type() throws Exception {
  Object actual=transcoder.decodeInternal(PropertyType.class,"SIMPLE");
  assertThat(actual).isEqualTo(SIMPLE);
}
