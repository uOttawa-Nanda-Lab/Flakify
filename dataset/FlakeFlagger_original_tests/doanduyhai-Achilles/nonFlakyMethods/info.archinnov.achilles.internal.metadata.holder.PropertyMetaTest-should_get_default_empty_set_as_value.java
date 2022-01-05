@Test public void should_get_default_empty_set_as_value() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).type(SET).build();
  pm.setEmptyCollectionAndMapIfNull(true);
  final Object actual=pm.nullValueForCollectionAndMap();
  assertThat(actual).isNotNull().isInstanceOf(Set.class);
}
