@Test public void should_encode() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).type(SIMPLE).build();
  Set<Object> actual=transcoder.encode(pm,Sets.newHashSet("value"));
  assertThat(actual).containsExactly("value");
}
