@Test public void should_encode_enum_type() throws Exception {
  Object actual=transcoder.encodeInternal(PropertyType.class,SIMPLE);
  assertThat(actual).isEqualTo("SIMPLE");
}
