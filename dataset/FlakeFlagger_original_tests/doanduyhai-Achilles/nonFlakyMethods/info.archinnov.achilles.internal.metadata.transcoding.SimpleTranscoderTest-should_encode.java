@Test public void should_encode() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).type(SIMPLE).build();
  Object actual=transcoder.encode(pm,"value");
  assertThat(actual).isEqualTo("value");
}
