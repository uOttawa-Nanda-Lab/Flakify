@Test public void should_get_empty_map_value_from_row() throws Exception {
  when(pm.type()).thenReturn(PropertyType.MAP);
  when(row.isNull("property")).thenReturn(true);
  final HashMap<Object,Object> emptyMap=new HashMap<>();
  when(pm.nullValueForCollectionAndMap()).thenReturn(emptyMap);
  Object actual=invoker.invokeOnRowForFields(row,pm);
  assertThat(actual).isSameAs(emptyMap);
}
