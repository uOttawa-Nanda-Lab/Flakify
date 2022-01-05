@Test public void should_get_default_empty_map_as_value() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.keyValueClass(String.class,Object.class).type(MAP).build();
  pm.setEmptyCollectionAndMapIfNull(true);
  final Object actual=pm.nullValueForCollectionAndMap();
  assertThat(actual).isNotNull().isInstanceOf(Map.class);
}
