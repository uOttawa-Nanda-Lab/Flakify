@Test public void should_encode_null_components() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).type(EMBEDDED_ID).compClasses(Long.class,String.class,PropertyType.class).build();
  List<Object> actual=transcoder.encodeToComponents(pm,Arrays.<Object>asList(null,null));
  assertThat(actual).isEmpty();
}
