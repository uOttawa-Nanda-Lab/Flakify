@Test public void should_return_null_if_no_fqcn() throws Exception {
  PropertyMeta propertyMeta=PropertyMetaTestBuilder.valueClass(Long.class).type(PropertyType.COUNTER).build();
  assertThat(propertyMeta.fqcn()).isNull();
}
