@Test public void should_encode() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.keyValueClass(Integer.class,String.class).type(SIMPLE).build();
  Map<Object,Object> actual=transcoder.encode(pm,ImmutableMap.of(1,"value"));
  assertThat(actual).containsKey(1);
  assertThat(actual).containsValue("value");
}
