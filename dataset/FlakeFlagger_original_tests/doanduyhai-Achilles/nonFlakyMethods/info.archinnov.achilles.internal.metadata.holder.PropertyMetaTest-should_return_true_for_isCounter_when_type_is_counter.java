@Test public void should_return_true_for_isCounter_when_type_is_counter() throws Exception {
  PropertyMeta propertyMeta=PropertyMetaTestBuilder.keyValueClass(Void.class,String.class).type(PropertyType.COUNTER).build();
  assertThat(propertyMeta.isCounter()).isTrue();
}
