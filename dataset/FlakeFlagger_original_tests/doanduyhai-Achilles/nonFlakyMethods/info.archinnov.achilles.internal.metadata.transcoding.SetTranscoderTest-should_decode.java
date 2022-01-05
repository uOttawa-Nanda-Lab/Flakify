@Test public void should_decode() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).type(SIMPLE).build();
  Set<Object> actual=transcoder.decode(pm,Sets.newHashSet("value"));
  assertThat(actual).containsExactly("value");
}
