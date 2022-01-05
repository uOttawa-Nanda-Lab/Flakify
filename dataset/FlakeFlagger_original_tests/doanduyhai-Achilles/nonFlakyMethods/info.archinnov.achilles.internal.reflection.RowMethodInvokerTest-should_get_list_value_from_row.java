@SuppressWarnings({"unchecked","rawtypes"}) @Test public void should_get_list_value_from_row() throws Exception {
  when(pm.type()).thenReturn(PropertyType.LIST);
  List<String> list=Arrays.asList("value");
  when(row.getList("property",String.class)).thenReturn(list);
  when(pm.decode(list)).thenReturn((List)list);
  Object actual=invoker.invokeOnRowForFields(row,pm);
  assertThat((List)actual).containsAll(list);
}
