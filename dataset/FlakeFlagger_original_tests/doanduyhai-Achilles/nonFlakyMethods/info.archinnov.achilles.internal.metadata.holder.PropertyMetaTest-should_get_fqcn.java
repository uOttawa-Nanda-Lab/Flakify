@Test public void should_get_fqcn() throws Exception {
  PropertyMeta propertyMeta=PropertyMetaTestBuilder.valueClass(Long.class).type(PropertyType.COUNTER).fqcn("fqcn").build();
  assertThat(propertyMeta.fqcn()).isEqualTo("fqcn");
}
