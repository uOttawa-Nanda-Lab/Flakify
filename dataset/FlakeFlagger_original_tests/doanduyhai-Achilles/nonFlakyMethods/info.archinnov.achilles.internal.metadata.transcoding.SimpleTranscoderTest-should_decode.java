@Test public void should_decode() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).type(SIMPLE).build();
  Object actual=transcoder.decode(pm,"value");
  assertThat(actual).isEqualTo("value");
}
