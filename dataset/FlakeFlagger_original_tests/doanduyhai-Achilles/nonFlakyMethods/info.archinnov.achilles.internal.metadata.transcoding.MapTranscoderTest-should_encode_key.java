@Test public void should_encode_key() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.keyValueClass(Integer.class,String.class).type(MAP).build();
  assertThat(transcoder.encodeKey(pm,11)).isEqualTo(11);
}
