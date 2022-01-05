@Test public void should_get_empty_set_value_from_row() throws Exception {
  when(pm.type()).thenReturn(PropertyType.SET);
  when(row.isNull("property")).thenReturn(true);
  final HashSet<Object> emptySet=new HashSet<>();
  when(pm.nullValueForCollectionAndMap()).thenReturn(emptySet);
  Object actual=invoker.invokeOnRowForFields(row,pm);
  assertThat(actual).isSameAs(emptySet);
}
