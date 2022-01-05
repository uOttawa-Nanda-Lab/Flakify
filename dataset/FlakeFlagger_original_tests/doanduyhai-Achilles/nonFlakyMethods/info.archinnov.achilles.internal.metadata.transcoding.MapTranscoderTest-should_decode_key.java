@Test public void should_decode_key() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.keyValueClass(Integer.class,String.class).type(MAP).build();
  assertThat(transcoder.decodeKey(pm,11)).isEqualTo(11);
}
