@Test public void should_decode() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).type(SIMPLE).build();
  List<Object> actual=transcoder.decode(pm,Arrays.asList("value"));
  assertThat(actual).containsExactly("value");
}
