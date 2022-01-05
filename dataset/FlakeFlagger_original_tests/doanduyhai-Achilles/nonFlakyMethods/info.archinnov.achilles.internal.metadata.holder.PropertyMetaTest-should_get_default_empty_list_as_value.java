@Test public void should_get_default_empty_list_as_value() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).type(LIST).build();
  pm.setEmptyCollectionAndMapIfNull(true);
  final Object actual=pm.nullValueForCollectionAndMap();
  assertThat(actual).isNotNull().isInstanceOf(List.class);
}
