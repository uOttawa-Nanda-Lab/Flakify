@Test public void should_get_empty_list_value_from_row() throws Exception {
  when(pm.type()).thenReturn(PropertyType.LIST);
  when(row.isNull("property")).thenReturn(true);
  final ArrayList<Object> emptyList=new ArrayList<>();
  when(pm.nullValueForCollectionAndMap()).thenReturn(emptyList);
  Object actual=invoker.invokeOnRowForFields(row,pm);
  assertThat(actual).isSameAs(emptyList);
}
