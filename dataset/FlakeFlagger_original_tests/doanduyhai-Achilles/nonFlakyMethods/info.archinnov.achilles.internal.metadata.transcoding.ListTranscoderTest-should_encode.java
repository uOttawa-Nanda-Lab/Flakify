@Test public void should_encode() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).type(SIMPLE).build();
  List<Object> actual=transcoder.encode(pm,Arrays.asList("value"));
  assertThat(actual).containsExactly("value");
}
