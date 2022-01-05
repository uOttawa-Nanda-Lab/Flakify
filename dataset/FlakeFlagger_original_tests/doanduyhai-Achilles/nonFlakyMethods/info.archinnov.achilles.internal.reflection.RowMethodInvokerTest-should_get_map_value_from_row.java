@SuppressWarnings({"unchecked","rawtypes"}) @Test public void should_get_map_value_from_row() throws Exception {
  when(pm.type()).thenReturn(PropertyType.MAP);
  Map<Integer,String> map=ImmutableMap.of(11,"value");
  when(row.getMap("property",Integer.class,String.class)).thenReturn(map);
  when(pm.decode(map)).thenReturn((Map)map);
  Object actual=invoker.invokeOnRowForFields(row,pm);
  assertThat((Map)actual).containsKey(11);
  assertThat((Map)actual).containsValue("value");
}
